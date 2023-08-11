package cn.hnu.operator.controller;

import cn.hnu.operator.constant.MyConst;
import cn.hnu.operator.product.SynContainer;
import cn.hnu.operator.resp.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

  @RequestMapping("/start")
  public Result start() {
    MyConst.ISRUNNING = true;
    return Result.OK();
  }
  // 返回容器情况，可能并不是全部线程停下来后的容器情况
  @RequestMapping("/pause")
  public Result pause() {
    MyConst.ISRUNNING = false;
    return new Result("20001","success", container.getProductsString());
  }
  @RequestMapping("/consumerspeed")
  public Result consumerspeed(int speed) {
    MyConst.CONSUMERSPEED = speed;
    return Result.OK();
  }
  @RequestMapping("/producerspeed")
  public Result producerspeed(int speed) {
    MyConst.PRODUCESPEED = speed;
    return Result.OK();
  }

}
