package cn.hnu.operator.resp;

/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
public class Result {
  String code;
  String msg;
  Object data;

  public Result(String code, String msg, Object data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }
  public static Result OK(){
    return new Result("20001", "success", null);
  }
  public static Result NOK(){
    return new Result("50001", "error", null);
  }
}
