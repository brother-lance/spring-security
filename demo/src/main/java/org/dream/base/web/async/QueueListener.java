package org.dream.base.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 项目名称：security
 * 类 名 称：QueueListener
 * 类 描 述：
 * 创建时间：2020/3/18 19:52
 * 创 建 人：Lance.WU
 * <p>
 * <p>
 * 创建一个是监听器
 */
@Slf4j
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    MockQueue mockQueue;

    @Autowired
    DeferredResultHolder holder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        // 当前是Java启动时会执行的方法，此处需要一个独立的进程监听，不然会阻塞项目启动阻塞
        new Thread(() -> {

            /**
             * 此处相当于接收了，消息队列的内容
             */

            while (true) {

                if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {

                    String orderNo = mockQueue.getCompleteOrder();
                    log.info("返回订单处理结果" + orderNo);

                    //holder.getResult().get(orderNo).setErrorResult("place order is complete fail");// 返回错误信息
                    holder.getResult().get(orderNo).setResult("place order is complete");
                    mockQueue.setCompleteOrder(null);

                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }
}
