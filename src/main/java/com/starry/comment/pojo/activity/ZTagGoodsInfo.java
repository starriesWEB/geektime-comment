package com.starry.comment.pojo.activity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author starry
 * @version 1.0
 * @date 2022/2/28 20:23
 * @Description
 */
@Data
public class ZTagGoodsInfo {

    private Integer id;
    private String name;
    // 2022-03-01T17:00:06+08:00
    private LocalDateTime integral_at;
    private String price;
    private Integer sold;
    private Integer stock;

    private Integer limit;
}
