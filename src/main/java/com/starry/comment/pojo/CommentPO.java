package com.starry.comment.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author starry
 * @version 1.0
 * @date 2021/12/14 14:44
 * @Description
 * https://time.geekbang.org/serv/v1/comments
 */
@Data
public class CommentPO implements Serializable {

    private Boolean can_delete;
    private String comment_content;
    private Boolean comment_ctime;
    private String comment_is_top;
    private Integer discussion_count;
    private Boolean had_liked;
    private Integer id;
    private Integer like_count;
    private Integer product_id;
    private String product_type;
    private Integer race_medal;
    private List<Reply> replies;
    private String score;
    private String ucode;
    private Integer uid;
    private String user_header;
    private String user_name;
}
