package cn.hnu.operator;

import cn.hnu.operator.service.constant.MyConst;
import cn.hnu.operator.service.consumer.Consumer;
import cn.hnu.operator.service.constant.SynContainer;
import cn.hnu.operator.service.producer.Productor;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
public class Starter {
  // app-orange ，启动！
    public void start(){
      SynContainer container = new SynContainer(); //缓冲区
      CountDownLatch cdl = new CountDownLatch(1); //同步20、20、20、20生产者消费者
      // 生产苹果
      for (int i = 1; i <= MyConst.PROCDUCENUM; i++) {
        new Productor(container, i, MyConst.APPLE, cdl).start();
      }
      // 消费苹果
      for (int i = 1; i <= MyConst.CONSUMERNUM; i++) {
        new Consumer(container, i, MyConst.APPLE, cdl).start();
      }
      // 生产橘子
      for (int i = 1; i <= MyConst.PROCDUCENUM; i++) {
        new Productor(container, i, MyConst.ORANGE, cdl).start();
      }
      // 消费橘子
      for (int i = 1; i <= MyConst.CONSUMERNUM; i++) {
        new Consumer(container, i, MyConst.ORANGE, cdl).start();
      }
    }
}
