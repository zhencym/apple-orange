package cn.hnu.operator.service.producer;

import cn.hnu.operator.service.constant.MyConst;
import cn.hnu.operator.service.product.Apple;
import cn.hnu.operator.service.product.ProductType;
import cn.hnu.operator.service.constant.SynContainer;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 生产者线程
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
//生产者
public class Productor extends Thread {

  SynContainer container;
  private int id; //生产者id
  private String productName; //生产产品名字

  private CountDownLatch cdl; // 同步启动

  Random random;


  public Productor(SynContainer container, int id, String productName, CountDownLatch cdl) {
    this.container = container;
    this.id = id;
    this.productName = productName;
    this.cdl = cdl;
    this.random = new Random(productName.charAt(0)+id);
  }

  //生产
  @Override
  public void run() {
//    try {
//      cdl.await();
//    } catch (InterruptedException e) {
//      throw new RuntimeException(e);
//    }

    // 死循环
    while (true) {
      // 控制生产
      while (MyConst.ISRUNNING) {
        try {
          Thread.sleep(random.nextInt(MyConst.PRODUCESPEED * 1000) + MyConst.PRODUCESPEED * 1000/2); //生产速度,避免同时睡去
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        ProductType product = null;
        if (productName.equals(MyConst.APPLE)) {
          // 生产一个苹果
           product = new Apple(MyConst.PRODUCTID++, -1,id,MyConst.APPLE);
        } else if (productName.equals(MyConst.ORANGE)){
          // 生产一个橘子
          product = new Apple(MyConst.PRODUCTID++, -1,id,MyConst.ORANGE);
        }
//        System.out.println(productName+"生产者 " + id + " 准备生产"+ productName);

        container.push(product, id);
      }
    }
  }
}
