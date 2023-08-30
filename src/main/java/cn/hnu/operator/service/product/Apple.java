package cn.hnu.operator.service.product;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */

public class Apple extends ProductType {

  public Apple(int id, int consumeId, int produceId, String name) {
    super(id, consumeId, produceId, name);
  }

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getProduceId() {
    return produceId;
  }

  @Override
  public int getConsumeId() {
    return consumeId;
  }

  @Override
  public void setConsumeId(int consumeId) {
    this.consumeId = consumeId;
  }
  @Override
  public String toString() {
    return "Apple{" +
            "id=" + id +
            ", consumeId=" + consumeId +
            ", produceId=" + produceId +
            ", name='" + name + '\'' +
            '}';
  }
}
