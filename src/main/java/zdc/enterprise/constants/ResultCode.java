package zdc.enterprise.constants;

public enum  ResultCode {

    SUCCESS(2000,"操作成功"),

    NO_LOGIN(3001,"请重新登录"),
    NO_AUTH(3002,"您还没有此功能的权限"),
    NO_UPDATE_DATA(3003,"该数据不能操作"),
    NOT_ACCESS(3004,"禁止访问"),










    FAIL(5000,"操作失败"),
    //自定义操作失败的内容
    CUSTOM_FAIL(5001,"自定义错误"),


    /*其他的操作错误,写在这里*/

    ;




    private int code;
    private String msg;

    public int code(){
        return this.code ;
    }
    public String msg(){
        return this.msg ;
    }

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
