package cn.hnu.operator.product;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
public class Apple implements Product{
  int id; //产品编号
  String name; // 产品名字
  public Apple(int id, String name){
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
}
