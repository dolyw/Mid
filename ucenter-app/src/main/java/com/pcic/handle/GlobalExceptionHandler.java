package com.pcic.handle;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.pcic.Constants;
import com.pcic.core.common.dto.ResponseMessage;
import com.pcic.exception.MessageException;
import com.pcic.exception.ValidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;

/**
 * 全局异常控制处理器
 * 未找到指定异常处理方法exception，则以全局异常方法globalException兜底
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/10/12 15:46
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private HttpServletRequest request;

    /**
     * 捕捉MessageException自定义异常
     *
     * @param e
     * @return com.pcic.app.common.dto.ResponseMessage
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/10/12 16:08
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MessageException.class)
    public Object exception(MessageException e) {
        // log.error("{}接口MessageException异常:{}", request.getRequestURI(), ExceptionUtil.stacktraceToOneLineString(e, 10000), e);
        return ResponseMessage.fail(e.getMessage());
    }

    /**
     * 捕捉ValidException自定义异常
     *
     * @param e
     * @return com.pcic.app.common.dto.ResponseMessage
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/10/12 16:08
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ValidException.class)
    public Object exception(ValidException e) {
        // log.error("{}接口ValidException异常:{}", request.getRequestURI(), ExceptionUtil.stacktraceToOneLineString(e, 10000), e);
        return ResponseMessage.fail("校验异常:" + e.getMessage());
    }

    /**
     * 捕捉RejectedExecutionException-线程拒绝策略异常
     *
     * @param e
     * @return java.lang.Object
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/9 13:49
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(RejectedExecutionException.class)
    public Object exception(RejectedExecutionException e) {
        log.error("{}接口线程服务异常:{}", request.getRequestURI(), ExceptionUtil.stacktraceToOneLineString(e.getCause(), 10000), e);
        return ResponseMessage.fail(e.getMessage());
    }

    /**
     * 捕捉ExecutionException-FutureTask异步线程异常
     *
     * @param e
     * @return java.lang.Object
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/9 13:46
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ExecutionException.class)
    public Object exception(ExecutionException e) {
        if (ObjectUtil.isNotEmpty(e.getCause().getMessage())) {
            log.error("{}接口异步线程服务异常:{}", request.getRequestURI(), ExceptionUtil.stacktraceToOneLineString(e.getCause(), 10000), e);
            return ResponseMessage.fail(e.getCause().getMessage());
        } else {
            log.error("{}接口异步线程服务异常:{}", request.getRequestURI(), ExceptionUtil.stacktraceToOneLineString(e, 10000), e);
            return ResponseMessage.fail(e.getMessage());
        }
    }

    /**
     * 捕捉404未找到路径异常
     *
     * @param e
     * @return java.lang.Object
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/10/12 16:08
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Object exception(NoHandlerFoundException e) {
        return ResponseMessage.fail(e.getMessage());
    }

    /**
     * 捕捉其他所有Exception异常
     *
     * @param e
     * @return com.pcic.app.common.dto.ResponseMessage
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/10/12 16:09
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public Object globalException(Exception e) {
        log.error("{}接口服务异常:{}", request.getRequestURI(), ExceptionUtil.stacktraceToOneLineString(e, 10000), e);
        return ResponseMessage.fail(e.getMessage());
    }
}
