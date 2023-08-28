package cn.hnu.operator.controller;

import cn.hnu.operator.service.constant.MyConst;
import cn.hnu.operator.service.constant.SynContainer;
import cn.hnu.operator.service.product.Product;
import cn.hnu.operator.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
@RestController
public class controller {
  @Autowired
  SynContainer container;

  /**
   * @return 实时返回容器状态,也就是返回当前cacheData中的数据
   */
  @RequestMapping("/tick")
  public Result tick(){
    // 1.将cacheData转化为对象数组
    Product[] cacheData = MyConst.cacheData.toArray(new Product[MyConst.cacheData.size()]);
    return new Result(20001,cacheData);
  }

  /**
   * 传入消费速度、生产速度，清空容器并开始运行
   * @param consumerSpeed
   * @param produceSpeed
   * @return
   */
  @RequestMapping("/clearandstart")
    public Result clearAndstart(int consumerSpeed,int produceSpeed){
    return Result.OK();
  }

  /**
   * 任务暂停后恢复运行
   * @return
   */
  @RequestMapping("/resume")
  public Result start() {
    MyConst.ISRUNNING = true;
    return Result.OK();
  }

  /**
   * 暂停生产/消费
   * @return
   */
  // 返回容器情况，可能并不是全部线程停下来后的容器情况
  @RequestMapping("/pause")
  public Result pause() {
    MyConst.ISRUNNING = false;
    return new Result(20001, container.getProductsString());
  }

  /**
   * 传入消费速度，主要用于在运行过程中消费速度的切换
   * @param speed
   * @return
   */
  @RequestMapping("/consumerSpeed")
  public Result consumerspeed(int speed) {
    MyConst.CONSUMERSPEED = speed;
    return Result.OK();
  }

  /**
   * 传入生产速度，主要用于在运行过程中生产速度的切换
   * @param speed
   * @return
   */
  @RequestMapping("/producerSpeed")
  public Result producerspeed(int speed) {
    MyConst.PRODUCESPEED = speed;
    return Result.OK();
  }

}
