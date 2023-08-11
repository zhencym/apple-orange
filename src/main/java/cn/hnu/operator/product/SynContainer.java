package cn.hnu.operator.product;

import cn.hnu.operator.constant.MyConst;
import org.springframework.stereotype.Component;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
//缓冲区
@Component
public class SynContainer {

  //需要一个容器大小
  Product[] products = new Product[MyConst.CONTAINERSIZE];
  //容器计数器
  int count = 0;

  //生产者放入产品
  public synchronized void push(Product product, int id) {

//    System.out.println(product.getName()+"生产者 " + id + " 准备生产");

    //如果容器满了，就需要调度消费者消费
    if (count == MyConst.CONTAINERSIZE) {  // 不能是count==chickens.length
      //通知消费者消费，生产等待
      try {
        System.out.println("仓库已满，"+product.getName()+"生产者 " + id + " 暂停生产！");
        this.wait();
        return; //所以加上一个return，醒来后退出，重新获得锁才能push。
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    //如果没有满，我们就需要丢入产品
    products[count++] = product;
    System.out.println(
        product.getName()+"生产者 " + id + " 生产的 "+ product.getName()+ " " + product.getId() + "----" + "现在仓库还剩余 " + count
            + " 个苹果或者橘子");
    System.out.println("缓冲区产品情况为：");
    System.out.println(getProductsString());
    System.out.println();

    //可以通知消费者消费了
    this.notifyAll();

  }

  /**
   * 消费者消费产品
   * @param id 消费者id
   * @param productName 消费产品名字
   * @return
   */
  public synchronized Product pop(int id, String productName) {

    //判断能否消费
    if (count == 0) {
      //等待生产者生产，消费者等待
      try {
        System.out.println("仓库已空，"+productName+"消费者 " + id + " 暂停消费！");
        this.wait();
        return null; //所以加上一个return，醒来后退出，重新获得锁才能pop。
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // 产品不对，不能消费
    if (!products[count-1].getName().equals(productName)) {
      return null;
    }

    //如果可以消费
    Product product = products[--count];
    System.out.println(
        product.getName()+"消费者 " + id + " 消费了 " + product.getName() + " " + product.getId() +"----" + "现在仓库还剩余 " + count
            + " 个苹果或者橘子");
    System.out.println("缓冲区产品情况为：");
    System.out.println(getProductsString());
    System.out.println();

    //吃完了，通知生产者生产
    this.notifyAll();
    return product;
  }

  public Product[] getProducts(){
    return products;
  }

  public String getProductsString(){
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < count; i++) {
      sb.append(products[i].getName() + " " + products[i].getId() + "| ");
    }
    return sb.toString();
  }
}
