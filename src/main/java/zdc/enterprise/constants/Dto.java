package zdc.enterprise.constants;

import javax.validation.groups.Default;

/**
 * 验证group 需要的可以自己添加 当继承Default时 ,验证对应添加groups的对象时 ,也会验证没有groups属性的 数据
 */
public class Dto {

    public interface Delete extends Default {}
    public interface Save extends Default {}
    public interface Update extends Default {}
    public interface Id extends Default {}
}
