package com.starry.comment.service;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.starry.comment.pojo.ArticleData;
import com.starry.comment.pojo.ChapterData;
import com.starry.comment.pojo.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author starry
 * @version 1.0
 * @date 2021/12/15 11:32
 * @Description
 */
@Service
public class CommentServiceImpl {

    public CommentDTO getComment(String aid, String prev) {
        String url = "https://time.geekbang.org/serv/v1/comments";
        HashMap<String, Object> map = new HashMap<>();
        map.put("aid", aid);
        map.put("prev", prev);
        String jsonStr = JSONUtil.toJsonStr(map);
        String body = HttpRequest.post(url)
                .header(Header.REFERER, "https://time.geekbang.org/column/article/70848")
                .header(Header.CONTENT_TYPE, "application/json")
                .body(jsonStr)
                .execute().body();
        return JSONUtil.toBean(body, CommentDTO.class);
    }

    /**
     * 获取具体课程信息
     * @param cid
     * @return
     */
    public HashMap<String, Object> getArticles(String cid){
        List<String> list = getChapters(cid);
        String url = "https://time.geekbang.org/serv/v1/column/articles";
        HashMap<String, Object> map = new HashMap<>();
        map.put("chapter_ids", list);
        map.put("cid", cid);
        map.put("order", "earliest");
        map.put("prev", "0");
        map.put("sample", false);
        map.put("size", 100);
        String jsonStr = JSONUtil.toJsonStr(map);
        String body = HttpRequest.post(url)
                .header(Header.REFERER, "https://time.geekbang.org/column/article/68319")
                .header(Header.CONTENT_TYPE, "application/json")
                .body(jsonStr)
                .execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);

        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray jsonArray = data.getJSONArray("list");
        List<ArticleData> articleData = jsonArray.toList(ArticleData.class);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("code", 0);
        hashMap.put("list", articleData);
        return hashMap;
    }

    /**
     * 获取大章节信息
     * @param cid
     * @return
     */
    public List<String> getChapters(String cid) {
        String url = "https://time.geekbang.org/serv/v1/chapters";
        HashMap<String, Object> map = new HashMap<>();
        map.put("cid", cid);
        String jsonStr = JSONUtil.toJsonStr(map);
        String body = HttpRequest.post(url)
                .header(Header.REFERER, "https://time.geekbang.org/column/article/68319")
                .header(Header.CONTENT_TYPE, "application/json")
                .body(jsonStr)
                .execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        JSONArray data = JSONUtil.parseArray(jsonObject.getStr("data"));
        List<ChapterData> chapterData = data.toList(ChapterData.class);
        return chapterData.stream().map(ChapterData::getId).collect(Collectors.toList());
    }

}
