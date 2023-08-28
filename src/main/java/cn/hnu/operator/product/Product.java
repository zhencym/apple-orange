package cn.hnu.operator.product;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
//产品
public abstract class Product{

      int id; //产品编号
      int consumeId; // 消费者id
      int produceId; // 生产者id
      String name; // 产品名字


      public abstract int getId();
      public abstract String getName();

      public abstract int getProduceId();
      public abstract int getConsumeId();
}

