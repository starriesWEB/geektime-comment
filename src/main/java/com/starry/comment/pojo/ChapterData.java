package com.starry.comment.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author starry
 * @version 1.0
 * @date 2021/12/16 16:30
 * @Description
 */
@Data
public class ChapterData implements Serializable {

    private Integer article_count;
    private String id;
    private Integer source_id;
    private String title;
}
