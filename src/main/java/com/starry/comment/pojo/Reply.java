package com.starry.comment.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author starry
 * @version 1.0
 * @date 2021/12/15 11:28
 * @Description
 */
@Data
public class Reply implements Serializable {

    private Integer comment_id;
    private String content;
    private Integer ctime;
    private String id;
    private String uid;
    private String user_name;
    private String user_name_real;
    private Integer utype;
}
