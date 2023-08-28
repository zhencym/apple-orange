package cn.hnu.operator.service.constant;

import cn.hnu.operator.service.product.Product;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

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
  public static int PRODUCESPEED = 2;
  /**
   * 消费速度（越小越快）
   */
  public static int CONSUMERSPEED = 10;
  /**
   * 消费者数量
   */
  public static int CONSUMERNUM = 1;
  /**
   * 生产者数量
   */
  public static int PROCDUCENUM = 1;

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
   * 实时缓冲区(容器)数据,因为消费时，后生产的先消费，因此使用一个双端队列实现
   */
  public static Deque<Product> cacheData = new ArrayDeque<>();

  /**
   * 目前已经被消费的产品数据，使用一个队列进行记录
   */
  public static Queue<Product> consumedData = new ArrayDeque<>();



}
