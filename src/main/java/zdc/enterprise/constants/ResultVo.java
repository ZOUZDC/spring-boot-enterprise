package zdc.enterprise.constants;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;

@Component
public class ResultVo implements Serializable {

    private int code;
    private String msg;
    private Object data ;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public Object getData() {
        return data;
    }

    public ResultVo setData(Object data) {
        this.data = data;
        return this;
    }


    private ResultVo() {}

    private ResultVo(ResultCode resultCode) {
        this.code = resultCode.code();
        this.msg = resultCode.msg();
    }

    private ResultVo(ResultCode resultCode,String msg) {
        this.code = resultCode.code();
        this.msg = msg;
    }



    /***
     * 操作成功,当参数为1个,则将数据直接指向data字典, 当 参数为偶数个时,将单数参数设置为Map的key,每个随后的参数设置为Value
     * @param data
     * @return
     */
    public static ResultVo success(Object... data){
        ResultVo resultVo =  new ResultVo(ResultCode.SUCCESS);
        resultVo.data =integrationData(data);
        return resultVo;
    }



    /***
     * 自定义异常,需要返回异常信息
     * @param failMsg
     * @param data
     * @return
     */
    public static ResultVo fail(String failMsg,Object... data){
        if(data==null){
            return new ResultVo(ResultCode.CUSTOM_FAIL, failMsg);
        }
        ResultVo resultVo = new ResultVo(ResultCode.CUSTOM_FAIL, failMsg);
        resultVo.data =integrationData(data);
        return resultVo;
    }


    /***
     * 根据ResultCode 返回数据
     * @param resultCode
     * @return
     */
    public static ResultVo custom(ResultCode resultCode){
        return new ResultVo(resultCode);
    }





    /***
     * 未捕获的异常,系统调用,请不要主动调用
     * @return
     */
    public static ResultVo sysFail(){
        return new ResultVo(ResultCode.FAIL);
    }

    /***
     * 未捕获的异常,系统调用,请不要主动调用
     * @param failMsg
     * @param data
     * @return
     */
    public static ResultVo sysFail(String failMsg,Object... data){
        if(data==null){
            return new ResultVo(ResultCode.FAIL, failMsg);
        }
        ResultVo resultVo = new ResultVo(ResultCode.FAIL, failMsg);
        resultVo.data =integrationData(data);
        return resultVo;
    }



    /***
     * 将返回参数整理到data中
     * @param data
     * @return
     */
    private static Object integrationData(Object... data){

        if(data ==null){
            return null;
        }

        int length = data.length;

        if(length==1){
            return data[0];
        }

        if(length %2 ==1){
            throw new RuntimeException("返回结果参数数量不正确,应该为偶数,实际为:"+length);
        }

        if(length %2 ==0){
            HashMap<Object, Object> map = new HashMap<>();
            for (int i = 0; i < length; i+=2) {
               map.put(data[i],data[i+1]);
            }
            return map;
        }
        return "";

    }




}
