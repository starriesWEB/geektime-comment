package com.starry.comment.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author starry
 * @version 1.0
 * @date 2021/12/16 13:00
 * @Description
 */
@RestController
@RequestMapping("vue-admin-template/user")
@CrossOrigin
public class UserController {

    @PostMapping("login")
    public Map login(String username,String password) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 20000);
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", "admin-token");
        map.put("data",data);
        return map;
    }

    @GetMapping("info")
    public Map info(String token) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 20000);
        HashMap<String, Object> data = new HashMap<>();
        data.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        data.put("introduction", "I am a super administrator");
        data.put("name", "Super Admin");
        data.put("roles", Collections.singletonList("admin"));
        map.put("data",data);
        return map;
    }
}
