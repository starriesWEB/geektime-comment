package com.starry.comment.controller.activity;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.starry.comment.base.R;
import com.starry.comment.pojo.activity.ZTagGoodsInfo;
import com.starry.comment.service.activity.ZTagService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author starry
 * @version 1.0
 * @date 2022/2/28 19:31
 * @Description
 */
@RestController
@RequestMapping("zTag")
@CrossOrigin
public class ZTagController {

    @Resource
    ZTagService zTagService;

    String uuid = IdUtil.simpleUUID().substring(0, 16);

    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() - 4);

    @GetMapping("login")
    R login(String username, String password, Integer goodsId, Integer goodsNumber) {
        username = "13227579809";
        password = "lol101.com";
        String auth = zTagService.login(username, password, uuid);
        AtomicBoolean flag = new AtomicBoolean(false);

        // 交易时间
        LocalDateTime limitTime = (LocalDateTime) zTagService.goodsInfo(uuid, goodsId).get("limitTime");

        // 截至交易时间后一分钟
        LocalDateTime endTime = limitTime.plusMinutes(1);

        while (!flag.get()) {

            pool.submit(() -> {
                        if (flag.get()) {
                            pool.shutdownNow();
                        }
                        int i = RandomUtil.randomInt(100, 260);
                        try {
                            Thread.sleep(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        LocalDateTime now = LocalDateTime.now();
                        // 在交易时间之后 || 交易限制一分钟后 才不操作
                //&& now.isBefore(endTime)
                        if (now.isEqual(limitTime) || now.isAfter(limitTime) ) {
                            boolean b = zTagService.goodsOrders(uuid, goodsId, auth, goodsNumber);
                            if (b) {
                                flag.set(true);
                                pool.shutdownNow();
                            }
                        }

                    }
            );
        }

        return R.ok();
    }

    @GetMapping("getGoodsIdAndNumber")
    R getGoodsIdAndNumber() {
        LocalDateTime now = LocalDateTime.now();
        List<ZTagGoodsInfo> list = zTagService.homeList(uuid);
        System.out.println(list);
        list = list.stream().filter(zTagGoodsInfo -> zTagGoodsInfo.getIntegral_at().isAfter(now)).peek(zTagGoodsInfo -> {
            Integer limit = (Integer) zTagService.goodsInfo(uuid, zTagGoodsInfo.getId()).get("limitNumber");
            zTagGoodsInfo.setLimit(limit);
        }).collect(Collectors.toList());
        return R.ok(list);
    }

}
