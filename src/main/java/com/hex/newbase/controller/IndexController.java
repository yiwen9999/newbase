package com.hex.newbase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: hexuan
 * Date: 2019/2/13
 * Time: 11:20 AM
 */
@RestController
public class IndexController {
    @GetMapping("/index")
    public Object index() {
        return "hello this is new base project!!!";
    }
}
