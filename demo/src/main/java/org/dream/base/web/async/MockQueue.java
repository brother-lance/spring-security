package org.dream.base.web.async;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 项目名称：security
 * 类 名 称：MockQueue
 * 类 描 述：模式消息队列
 * 创建时间：2020/3/18 19:19
 * 创 建 人：Lance.WU
 * <p>
 * 模拟一个是消息队列，监听数据信息
 */
@Setter
@Getter
@Component
@Slf4j
public class MockQueue {

    private String placeOrder; // 模拟消息队列的中主题，下单主题

    private String completeOrder; // 模拟消息队列中的监听队列主题, 完成下单

    public void setPlaceOrder(String placeOrder) {

        // 当前是向消息队列中发送数据，则是异常处理。
        new Thread(() -> {
            log.info("接到下单请求:" + placeOrder);
            this.placeOrder = placeOrder;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.debug("订单处理时间为：1秒");
            }
            this.completeOrder = placeOrder;
            log.info("接到下单完毕:" + completeOrder);
        }).start();
    }
}
