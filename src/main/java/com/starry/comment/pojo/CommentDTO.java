package com.starry.comment.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author starry
 * @version 1.0
 * @date 2021/12/14 14:42
 * @Description
 */
@Data
public class CommentDTO  implements Serializable {
    private Integer code;
    private CommentData data;
    private String[] error;
    private String[] extra;


}
