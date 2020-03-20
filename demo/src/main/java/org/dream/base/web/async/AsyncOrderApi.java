package org.dream.base.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;



/**
 * 项目名称：security
 * 类 名 称：OrderApi
 * 类 描 述：订单-  异步请求
 * 创建时间：2020/3/18 19:04
 * 创 建 人：Lance.WU
 */
@Slf4j
@RestController
@RequestMapping("/api/1.0/order")
public class AsyncOrderApi {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder holder;


    /**
     * 同步处理
     * @return
     * @throws InterruptedException
     */
//    @RequestMapping
//    public Object synchTest() throws InterruptedException {
//        log.info("线程开始:");
//        Thread.sleep(1000);
//        log.info("线程结束:");
//        return "Success";
//    }

//    /**
//     * 异常处理
//     *
//     * @return 返回结果
//     * @throws InterruptedException
//     */
//    @RequestMapping
//
//    public Callable<String> oder() throws InterruptedException {
//        log.info("主线程开始");
//        Callable<String> callback = new Callable() {
//            @Override
//            public Object call() throws Exception {
//                log.info("子线程开始");
//                Thread.sleep(1000);
//                log.info("子线程结束");
//                return "Success";
//            }
//        };
//        log.info("主线程结束");
//        return callback;
//    }

    /**
     * 异常处理
     *
     * @return 返回结果
     * @throws InterruptedException
     */
    @RequestMapping
    public DeferredResult<String> oder() throws InterruptedException {
        log.info("主线程开始");

        String orderNo = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNo);
        DeferredResult<String> deferredResult = new DeferredResult();
        holder.getResult().put(orderNo, deferredResult);

        log.info("主线程结束");
        return deferredResult;
    }

    //异步完成
//    @RequestMapping(value = "/deferred", method = RequestMethod.GET, produces = "text/html")
//    public DeferredResult<String> executeSlowTask() {
//        log.info("Request received");
//        DeferredResult<String> deferredResult = new DeferredResult<>();
//        CompletableFuture.supplyAsync(taskService::execute)
//                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));
//        log.info("Servlet thread released");
//
//        return deferredResult;
//    }
}
