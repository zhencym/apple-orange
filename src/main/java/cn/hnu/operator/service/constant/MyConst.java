package cn.hnu.operator.service.constant;

import cn.hnu.operator.service.product.ProductType;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
public class MyConst {
  /**
   * 全局唯一产品编号
   */
  public static int PRODUCTID = 1;

  /**
   * 正在进行生产消费
   */
  public static boolean ISRUNNING = true;
  /**
   * 生产速度（越小越快）
   */
  public volatile static int PRODUCESPEED = 4;
  /**
   * 消费速度（越小越快）
   */
  public volatile static int CONSUMERSPEED = 2;
  /**
   * 消费者数量
   */
  public static int CONSUMERNUM = 2;
  /**
   * 生产者数量
   */
  public static int PROCDUCENUM = 2;

  /**
   * 生产者产品名字apple
   */
  public static String APPLE = "apple";

  /**
   * 生产者产品名字orange
   */
  public static String ORANGE = "orange";

  /**
   * 容量
   */
  public static int CONTAINERSIZE = 20;

  /**
   * 可重入锁
   */
  public static ReentrantLock LOCK = new ReentrantLock();

  /**
   * 消费者等待队列
   */
  public static Condition CONSUMERQUE = LOCK.newCondition();
  /**
   * 生产者等待队列
   */
  public static Condition PRODUCERQUE = LOCK.newCondition();

  /**
   * SynContainer中的共享容器
   */
  public static ProductType[] products = new ProductType[MyConst.CONTAINERSIZE];

  //容器计数器
  public volatile static int count = 0;

  /**
   * 实时缓冲区(容器)数据,因为消费时，后生产的先消费，因此使用一个双端队列实现
   */
  public static Deque<ProductType> cacheData = new ArrayDeque<>();

  /**
   * 目前已经被消费的产品数据，使用一个队列进行记录
   */
  public static Queue<ProductType> consumedData = new ArrayDeque<>();



}
