package cn.hnu.operator.product;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
public class Orange extends Product{

  public Orange(int id, String name){
    this.id= id;
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
