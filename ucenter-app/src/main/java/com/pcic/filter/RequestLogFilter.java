package com.pcic.filter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.pcic.util.AddrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.WriteListener;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求响应日志过滤器
 *
 * @author wliduo[i@dolyw.com]
 * @date 2022/5/24 9:18
 */
@Component
@WebFilter(filterName = "RequestLogFilter", urlPatterns = "/**")
public class RequestLogFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestLogFilter.class);

    /**
     * 忽略URL
     */
    private static final List<String> IGNORE_URL = new ArrayList<String>(){{
        add("/favicon.ico");
        add("/doc.html");
        add("/webjars/**");
        add("/v3/api-docs/**");
    }};

    private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";

    private static final String MULTIPART_FORM_DATA = "multipart/form-data";

    private static final String LOGIN_USER_ID_KEY = "LOGIN_USER_ID";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long timeBegin = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // 请求属性获取
        String reqStr = "", respStr = "";
        String ip = AddrUtil.getRemoteAddr(request);
        String url = request.getRequestURI(), method = request.getMethod().toUpperCase();
        String contentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        // contentType = contentType != null ? contentType.toLowerCase() : APPLICATION_X_WWW_FORM_URLENCODED;
        Object userId = WebUtils.getSessionAttribute(request, LOGIN_USER_ID_KEY);
        String sessionId = WebUtils.getSessionId(request);
        // 忽略URL跳过
        if (match(url, IGNORE_URL)) {
            // logger.info("【日志统一过滤】入口：{\"ip\":\"{}\", \"url\":\"{}\", \"user\":\"{}\", \"id\":\"{}\"}", ip, url, userId, sessionId);
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 获取请求中的流取出来的字符串，再次转换成流，然后把它放入到新对象中
            RequestWrapper requestWrapper = new RequestWrapper(request);
            ResponseWrapper responseWrapper = new ResponseWrapper(response);
            // GET及application/x-www-form-urlencoded，multipart/form-data直接从Parameter获取参数
            Map paramMap = new HashMap(16);
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues.length == 1) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() != 0) {
                        paramMap.put(paramName, paramValue);
                    }
                }
            }
            // paramMap为空取Body
            if (paramMap.isEmpty()) {
                reqStr = requestWrapper.getBody();
            } else {
                reqStr = JSONUtil.toJsonStr(paramMap);
            }
            logger.info("【日志统一过滤】入口：{\"ip\":\"{}\", \"url\":\"{}\"}，请求参数：{}", ip, url, reqStr);
            filterChain.doFilter(requestWrapper, responseWrapper);
            respStr = responseWrapper.getBody();
            // 将返回值重新写入Response
            response.getOutputStream().write(respStr.getBytes());
            long timeEnd = System.currentTimeMillis();
            logger.info("【日志统一过滤】出口：{\"ip\":\"{}\", \"url\":\"{}\"}，请求参数：{}，返回参数：{}，耗时：{}秒",
                    ip, url, reqStr, respStr, (timeEnd - timeBegin) / 1000.0D);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    /**
     * 判断字符是否匹配
     * ?  表示单个字符
     * *  表示一层路径内的任意字符串，不可跨层级
     * ** 表示任意层路径
     *
     * @param urlPath 需要匹配的url
	 * @param urls 匹配规则
     * @return boolean
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2022/5/31 19:41
     */
    public static boolean match(String urlPath, List<String> urls) {
        if (StrUtil.isEmpty(urlPath) || CollUtil.isEmpty(urls)) {
            return false;
        }
        for (String url : urls) {
            AntPathMatcher matcher = new AntPathMatcher();
            if (matcher.match(url, urlPath)) {
                return true;
            }
        }
        return false;
    }

    /**
     * RequestWrapper
     *
     * @author wliduo[i@dolyw.com]
     * @date 2022/5/24 9:37
     */
    public static class RequestWrapper extends HttpServletRequestWrapper {

        private final String body;

        public RequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            StringBuilder stringBuilder = new StringBuilder("");
            try (InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream());
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
                // 不能使用byte，byte一个字节无法兼容中文字符会导致汉字乱码，char两个字节支持单个中文字符
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } finally {
                body = stringBuilder.toString();
            }
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
            ServletInputStream servletInputStream = new ServletInputStream() {
                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }

                @Override
                public int read() throws IOException {
                    return byteArrayInputStream.read();
                }
            };
            return servletInputStream;
        }

        public String getBody() {
            return this.body;
        }

    }

    /**
     * ResponseWrapper
     *
     * @author wliduo[i@dolyw.com]
     * @date 2022/5/24 9:37
     */
    public static class ResponseWrapper extends HttpServletResponseWrapper {

        private PrintWriter printWriter;
        private ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        public ResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public ServletOutputStream getOutputStream() throws IOException {
            // 将数据写到byte中
            return new CustomServletOutputStream(bytes);
        }

        /**
         * 重写父类的getWriter()方法，将响应数据缓存在PrintWriter中
         */
        @Override
        public PrintWriter getWriter() throws IOException {
            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bytes, "utf-8")) {
                printWriter =  new PrintWriter(outputStreamWriter);
            }
            return printWriter;
        }

        /**
         * 获取缓存在PrintWriter中的响应数据
         *
         * @return
         */
        public byte[] getBytes() {
            if (printWriter != null) {
                printWriter.close();
                return bytes.toByteArray();
            }

            if (bytes != null) {
                try {
                    bytes.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bytes.toByteArray();
        }

        public String getBody() throws IOException {
            return new String(this.getBytes(), "utf-8");
        }

        class CustomServletOutputStream extends ServletOutputStream {
            private ByteArrayOutputStream byteArrayOutputStream;

            public CustomServletOutputStream(ByteArrayOutputStream byteArrayOutputStream) {
                this.byteArrayOutputStream = byteArrayOutputStream;
            }

            @Override
            public void write(int b) throws IOException {
                // 将数据写到stream中
                byteArrayOutputStream.write(b);
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setWriteListener(WriteListener listener) {

            }
        }
    }
}
