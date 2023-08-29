package cn.hnu.operator.controller;

import cn.hnu.operator.service.constant.MyConst;
import cn.hnu.operator.service.constant.SynContainer;
import cn.hnu.operator.service.product.Product;
import cn.hnu.operator.utils.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

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
    Object[] cacheData = MyConst.cacheData.toArray(new Object[MyConst.cacheData.size()]);
//    System.out.println("转化成功");
//    for (Object o:cacheData
//         ) {
//      System.out.println(o.toString());
//    }
    return new Result(20001, cacheData);
  }

  /**
   * 实时返回消费产品历史记录，限制返回最近的20条历史记录
   * @return
   */
  @RequestMapping("/consumedTick")
  public Result consumedTick(){
    // 1.将consumedData转化为对象数组
    Object[] consumedData = MyConst.consumedData.toArray(new Object[MyConst.cacheData.size()]);
    System.out.println("转化成功");
    return new Result(20001, consumedData);
  }

  /**
   * 传入消费速度、生产速度，清空容器并开始运行
   * @param consumerSpeed
   * @param produceSpeed
   * @return
   */
  @RequestMapping("/clearandstart")
    public Result clearAndstart(int consumerSpeed,int produceSpeed){
    // 1.暂停并清空缓冲区、已消费区、容器
    pause();
    container.clear();
    System.out.println("清空完成");
    // 2.传入消费速度、生产速度
    MyConst.CONSUMERSPEED = consumerSpeed;
    MyConst.PRODUCESPEED = produceSpeed;
    // 3. 继续开始运行
    resume();
    System.out.println("开始运行");
    return Result.OK();
  }

  /**
   * 任务暂停后恢复运行
   * @return
   */
  @RequestMapping("/resume")
  public Result resume() {
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
    return Result.OK();
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
