package cn.hnu.operator.product;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
public class Apple extends Product{

  public Apple(int id, int consumeId, int produceId, String name) {
    this.id = id;
    this.consumeId = consumeId;
    this.produceId = produceId;
    this.name = name;
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
}
