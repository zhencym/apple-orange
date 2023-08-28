package cn.hnu.operator;

//测试：生产者消费者模型”---->利用缓冲区解决：管程法
//生产者，消费者，产品，缓冲区
//此方法生产者消费者都在同一个等待队列，容易唤醒错误对象，浪费资源。
//应该生产者消费者在不同等待队列。但咱不知道怎么实现
//使用的Object类里面自带的阻塞队列+sychronized锁 来实现生产者消费者模型

import cn.hnu.operator.service.constant.MyConst;
import cn.hnu.operator.service.consumer.Consumer;
import cn.hnu.operator.service.producer.Productor;
import cn.hnu.operator.service.constant.SynContainer;
import java.util.concurrent.CountDownLatch;

//使用同步容器，synchronized 、 休眠队列、notify、wait实现线程有序执行 的 生产者消费者问题。
public class TestPC {

  public static void main1(String[] args) {

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
    // app-orange ，启动！
//    cdl.countDown(); // 不同步效果好
  }
}



