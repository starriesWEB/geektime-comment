package com.starry.comment.service.activity;

import cn.hutool.core.lang.Dict;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.starry.comment.pojo.activity.ZTagGoodsInfo;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author starry
 * @version 1.0
 * @date 2022/2/28 19:34
 * @Description
 */
@Service
public class ZTagService {

    public String login(String username, String password, String uuid) {
        String url = "https://app.ztag.vip/v1/apis/login";
        HashMap<String, Object> map = new HashMap<>();
        map.put("phone", username);
        map.put("password", password);
        String jsonStr = JSONUtil.toJsonStr(map);

        String body = HttpRequest.post(url)
                .header("app-channel", "11")
                .header("app-uuid", uuid)
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-site")
                .header(Header.CONTENT_TYPE, "application/json")
                .header(Header.USER_AGENT, "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Mobile Safari/537.36")
                .body(jsonStr)
                .execute().body();
        System.out.println(body);
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String auth = jsonObject.getByPath("data.access_token", String.class);
        return auth;

    }

    public List<ZTagGoodsInfo> homeList(String uuid) {
        String url = "https://app.ztag.vip/v1/apis/home_list?page=1&page_size=10&homepage_category=0";

        String body = HttpRequest.get(url)
                .header("app-channel", "11")
                .header("app-uuid", uuid)
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-site")
                .header(Header.CONTENT_TYPE, "application/json")
                .header(Header.USER_AGENT, "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Mobile Safari/537.36")
                .execute().body();
        System.out.println(body);
        JSONObject jsonObject = JSONUtil.parseObj(body);
        JSONObject data = jsonObject.getJSONObject("data");
        JSONArray jsonArray = data.getJSONArray("result");
        List<ZTagGoodsInfo> list = jsonArray.toList(ZTagGoodsInfo.class);
        return list;
    }

    public Dict goodsInfo(String uuid, Integer goodsId) {
        String url = "https://app.ztag.vip/v1/apis/goods_info?id=" + goodsId;

        String body = HttpRequest.get(url)
                .header("app-channel", "11")
                .header("app-uuid", uuid)
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-site")
                .header(Header.CONTENT_TYPE, "application/json")
                .header(Header.USER_AGENT, "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Mobile Safari/537.36")
                .execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Integer limitNumber = jsonObject.getByPath("data.limit_num",Integer.class);
        LocalDateTime limitTime = jsonObject.getByPath("data.limit_at", LocalDateTime.class);
        return Dict.of("limitTime", limitTime, "limitNumber", limitNumber);
    }

    public boolean goodsOrders(String uuid, Integer goodsId, String auth, Integer number) {
        String url = "https://app.ztag.vip/v1/apis/goods_orders";
        if (number == null || number < 1) {
            number = 1;
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("amount", number);
        map.put("id", goodsId);
        String jsonStr = JSONUtil.toJsonStr(map);

        String body = HttpRequest.post(url)
                .header("app-channel", "11")
                .header("app-uuid", uuid)
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-site")
                .header("Authorization", "Bearer "+auth)
                .header(Header.CONTENT_TYPE, "application/json")
                .header(Header.USER_AGENT, "Mozilla/5.0 (Linux; Android 8.0; Pixel 2 Build/OPD3.170816.012) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Mobile Safari/537.36")
                .body(jsonStr)
                .execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        Integer code = jsonObject.getByPath("code",Integer.class);
        System.out.println(body);
        return code == 200 || code == 2000;
    }

}
