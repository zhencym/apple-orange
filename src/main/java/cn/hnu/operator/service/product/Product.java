package cn.hnu.operator.service.product;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
//产品
public abstract class Product{

      int id; //产品编号
      int consumeId; // 消费者id(id=-1时,还未被消费;反之已经被消费)
      int produceId; // 生产者id
      String name; // 产品名字

      public Product(int id, int consumeId, int produceId, String name) {
            this.id = id;
            this.consumeId = consumeId;
            this.produceId = produceId;
            this.name = name;
      }

      public abstract int getId();
      public abstract String getName();

      public abstract int getProduceId();
      public abstract int getConsumeId();

      public void setId(int id) {
            this.id = id;
      }

      public void setProduceId(int produceId) {
            this.produceId = produceId;
      }

      public void setName(String name) {
            this.name = name;
      }

      // 设置产品消费者ID
      public abstract void setConsumeId(int consumeId);


}

