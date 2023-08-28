package cn.hnu.operator.service.consumer;

import cn.hnu.operator.service.constant.MyConst;
import cn.hnu.operator.service.constant.SynContainer;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 消费者线程
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
//消费者
public class Consumer extends Thread {

  SynContainer container;
  private int id; //消费者id
  private String productName; //消费产品名字
  private CountDownLatch cdl; // 同步启动

  Random random;

  public Consumer(SynContainer container, int id, String productName, CountDownLatch cdl) {
    this.container = container;
    this.id = id;
    this.productName = productName;
    this.cdl = cdl;
    this.random = new Random(productName.charAt(0)+id);
  }

  //消费
  @Override
  public void run() {
//    try {
//      cdl.await();
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }

    // 死循环
    while (true) {
      // 控制生产，当正在生产时，
      while (MyConst.ISRUNNING) {
        try {
          Thread.sleep(random.nextInt(MyConst.CONSUMERSPEED * 1000) + MyConst.CONSUMERSPEED * 1000/2); //消费速度,避免同时睡去
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
//        System.out.println(productName+"消费者 " + id + " 准备消费"+ productName);
        container.pop(id,productName);
      }
    }
  }
}
