package cn.hnu.operator.resp;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
public class Result {
  int status;
//  String msg;
  Object data;

  public Result(int status,  Object data) {
    this.status = status;
    this.data = data;
  }
  public static Result OK(){
    return new Result(20001, null);
  }
  public static Result NOK(){
    return new Result(50001, null);
  }
}
