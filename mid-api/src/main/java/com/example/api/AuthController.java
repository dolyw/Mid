package com.example.api;

import com.example.service.BusinessService;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * AuthController
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/19 14:46
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    /**
     * logger
     */
    private final static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private BusinessService businessService;

    /**
     * 验证码
     *
     * @param key
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/10/27 17:29
     */
    @GetMapping("/captcha")
    public void captcha(HttpServletResponse response, @RequestParam("key") String key) throws Exception {
        // Png，验证码长宽，px单位，验证码4位长度
        Captcha captcha = new SpecCaptcha(130, 48, 4);
        // Gif
        // Captcha captcha = new GifCaptcha(130, 48, 4);
        // 验证码值的类型，1. 数字加字母，2. 纯数字，3. 纯字母
        captcha.setCharType(2);
        // 输出到response
        captcha.out(response.getOutputStream());
        // 设置到缓存
        // redisService.set(key, captcha.text().toLowerCase(), 60L);
        // Png，ContentType设置
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        // Gif
        // response.setContentType(MediaType.IMAGE_GIF_VALUE);
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "No-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }

    /**
     * 登录生成Token
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/18 15:31
     */
    @PostMapping("/token")
    public String token() {
        return "{\n" +
                "\t\"access_token\": \"022fab2c-470b-411d-ba96-02057381d0ae\",\n" +
                "\t\"token_type\": \"bearer\",\n" +
                "\t\"refresh_token\": \"9c3d7c16-3c66-4cac-8a04-a047efb7ed08\",\n" +
                "\t\"expires_in\": 86399,\n" +
                "\t\"scope\": \"all\"\n" +
                "}";
    }

    /**
     * 用户信息
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/10/27 17:32
     */
    @GetMapping("/user")
    public String user() throws Exception {
        return "{\n" +
                "\t\"principal\": {\n" +
                "\t\t\"password\": null,\n" +
                "\t\t\"username\": \"scott\",\n" +
                "\t\t\"accountNonExpired\": true,\n" +
                "\t\t\"accountNonLocked\": true,\n" +
                "\t\t\"credentialsNonExpired\": true,\n" +
                "\t\t\"enabled\": true,\n" +
                "\t\t\"userId\": 15,\n" +
                "\t\t\"avatar\": \"cnrhVkzwxjPwAaCfPbdc.png\",\n" +
                "\t\t\"email\": \"scott@hotmail.co\",\n" +
                "\t\t\"mobile\": \"13233123123\",\n" +
                "\t\t\"sex\": \"0\",\n" +
                "\t\t\"deptId\": 133,\n" +
                "\t\t\"deptName\": \"测试1\",\n" +
                "\t\t\"roleId\": \"2\",\n" +
                "\t\t\"roleName\": \"注册用户\",\n" +
                "\t\t\"lastLoginTime\": \"2021-11-18 14:45:46\",\n" +
                "\t\t\"description\": \"我是史考特\",\n" +
                "\t\t\"status\": \"1\",\n" +
                "\t\t\"deptIds\": \"1,2,4,5\"\n" +
                "\t}\n" +
                "}";
    }

    /**
     * 退出
     *
     * @param token
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/18 16:25
     */
    @PostMapping("/logout")
    public String logout(@RequestHeader("Authorization") String token) {
        // revokeToken(token);
        return "logout";
    }

    /**
     * msg
     *
     * @param
     * @return java.lang.String
     * @throws
     * @author wliduo[i@dolyw.com]
     * @date 2021/11/24 11:48
     */
    @GetMapping("/msg")
    public String msg() {
        return businessService.handle();
    }

}
