package com.liming.licensebuilder.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ltf
 * @date 2021-07-20 21:06
 */
@Slf4j
@RestController
public class LoginController {
    /**
     * 模拟登录验证
     * @param loginName 用户名
     * @param password 密码
     */
    @GetMapping(value = "/login")
    public Map<String,Object> test(@RequestParam(required = true) String loginName, @RequestParam(required = true) String password){
        Map<String,Object> result = new HashMap<>(1);
        log.info(MessageFormat.format("登录名称：{0}，密码：{1}",loginName,password));
        //模拟登录
        log.info("模拟登录被拦截检查证书可用性");
        result.put("code",200);
        return result;
    }
}
