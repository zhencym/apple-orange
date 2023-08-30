package cn.hnu.operator.service.constant;

import cn.hnu.operator.service.product.ProductType;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
//缓冲区
@Component
public class  SynContainer {
  public synchronized void clear() {
    MyConst.consumedData.clear();
    MyConst.cacheData.clear();
    MyConst.count = 0;
    Arrays.fill(MyConst.products,null);
  }

  //生产者放入产品
  public synchronized void push(ProductType product, int id) {

//    System.out.println(product.getName()+"生产者 " + id + " 准备生产");

    //如果容器满了，就需要调度消费者消费
    if (MyConst.count == MyConst.CONTAINERSIZE) {  // 不能是count==chickens.length
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
    MyConst.products[MyConst.count++] = product;
    MyConst.cacheData.add(product); // 将产品放入全局缓冲区

    System.out.println(
        product.getName()+"生产者 " + id + " 生产的 "+ product.getName()+ " " + product.getId() + "----" + "现在仓库还剩余 " + MyConst.count
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
  public synchronized ProductType pop(int id, String productName) {

    //判断能否消费
    if (MyConst.count == 0) {
      //等待生产者生产，消费者wait等待
      try {
        System.out.println("仓库已空，"+productName+"消费者 " + id + " 暂停消费！");
        this.wait();
        return null; //所以加上一个return，醒来后退出，重新获得锁才能pop。
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

    // 产品不对,不能消费
    if (!MyConst.products[MyConst.count-1].getName().equals(productName)) {
      return null;
    }

    //如果可以消费
    ProductType product = MyConst.products[--MyConst.count]; //那就消费最后一个
    product.setConsumeId(id); // 设置已被消费产品的消费者ID
    MyConst.cacheData.pollLast();// 将已经消费的产品(从后向前消费)从缓冲区弹出
    // 被消费历史记录，只保存最近的20条数据
    if(MyConst.consumedData.size() >= 20){
      MyConst.consumedData.poll();
    }
    MyConst.consumedData.add(product); // 将此时被消费的产品加入历史记录中


    System.out.println(
        product.getName()+"消费者 " + id + " 消费了 " + product.getName() + " " + product.getId() +"----" + "现在仓库还剩余 " + MyConst.count
            + " 个苹果或者橘子");
    System.out.println("缓冲区产品情况为:");
    System.out.println(getProductsString());
    System.out.println("consumedData情况为："+getConsumedString());
    System.out.println("cacheData情况为："+getCacheString());
    System.out.println();

    //吃完了，通知生产者生产
    this.notifyAll();
    return product;
  }


  public String getProductsString(){
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < MyConst.count; i++) {
      sb.append(MyConst.products[i].getName() + " " + MyConst.products[i].getId() + "| ");
    }
    return sb.toString();
  }

  public String getCacheString(){
    StringBuffer sb = new StringBuffer();
    for (ProductType p:MyConst.cacheData
         ) {
      sb.append(p.toString() + "|");
    }
    return sb.toString();
  }
  public String getConsumedString(){
    StringBuffer sb = new StringBuffer();
    for (ProductType p:MyConst.consumedData
         ) {
      sb.append(p.toString() + "|");
    }
    return sb.toString();
  }
}
