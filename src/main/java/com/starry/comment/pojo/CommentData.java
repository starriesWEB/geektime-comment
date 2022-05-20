package com.starry.comment.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author starry
 * @version 1.0
 * @date 2021/12/15 11:22
 * @Description
 */
@Data
public class CommentData implements Serializable {

    private List<CommentPO> list;
    private CommentPage page;
}
