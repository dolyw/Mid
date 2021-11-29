package com.pcic.service;

import org.springframework.stereotype.Service;

/**
 * Mid
 *
 * @author wliduo[i@dolyw.com]
 * @date 2021/11/24 11:46
 */
@Service
public class BusinessService {

    public String handle() {
        System.out.println("success");
        return "success";
    }

}
