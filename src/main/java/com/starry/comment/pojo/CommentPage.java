package com.starry.comment.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author starry
 * @version 1.0
 * @date 2021/12/15 11:23
 * @Description
 */
@Data
public class CommentPage implements Serializable {

    private Integer count;
    private Boolean more;
}
