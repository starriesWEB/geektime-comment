package com.starry.comment.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.starry.comment.pojo.CommentDTO;
import com.starry.comment.service.CommentServiceImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author starry
 * @version 1.0
 * @date 2021/12/15 11:47
 * @Description
 */
@RestController
@CrossOrigin
public class CommentController {

    @Resource
    private CommentServiceImpl commentService;

    @PostMapping("/comment")
    public CommentDTO getComment(@RequestBody String params) {
        JSONObject jsonObject = JSONUtil.parseObj(params);
        String aid = jsonObject.get("aid", String.class);
        String prev = jsonObject.get("prev", String.class);
        return commentService.getComment(aid,prev);
    }

    @PostMapping("/article")
    public HashMap<String, Object> getArticles(@RequestBody String params) {
        JSONObject jsonObject = JSONUtil.parseObj(params);
        String cid = jsonObject.get("cid", String.class);
        return commentService.getArticles(cid);
    }

}
