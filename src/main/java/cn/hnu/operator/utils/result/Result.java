package cn.hnu.operator.utils.result;


/**
 * @Author: zhencym
 * @DATE: 2023/8/11
 */
public class Result {
  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  int status;
//  String msg;
  Object data;

  //Object[] dataArray;

  public Result(int status,Object data) {
    this.status = status;
    this.data = data;
  }
//  public Result(int status,Object[] dataArray) {
//    this.status = status;
//    this.dataArray = dataArray;
//  }
  public static Result OK(){
    return new Result(20001, null);
  }
  public static Result NOK(){
    return new Result(50001, null);
  }
}
