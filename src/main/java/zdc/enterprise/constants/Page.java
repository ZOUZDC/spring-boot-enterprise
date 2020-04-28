package zdc.enterprise.constants;

import java.util.List;

public class Page<T> {

    //当前页 从1开始
    private Long current;
    //每页条数
    private Long size;
    //数据库开始的第一条
    private Long start;
    //是否分页 (主要用分页数据导出上,但是也需要考虑是否数据量过大,过大的话需要自己实现分页)
    private boolean fenye =true;

    //返回的数据列表 ,预留
    private List<T> list;
    //总条数  ,预留
    private Long  total ;


    public Page() {
        this(1L,10L);
    }

    public Page(Long current, Long size) {
        this.current = current;
        this.size = size;
        this.fenye =true;
    }

    public Long getCurrent() {
        return current;
    }

    public void setCurrent(Long current) {
        if(current==null||current<=0){
            this.current=1L;
        }
        this.current = current;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        if(size==null||size<=0){
            this.size=10L;
        }
        this.size = size;
    }

    public Long getStart() {
        return  (current-1)*size;
    }

    public void setStart(Long start) {

    }

    public boolean isFenye() {
        return fenye;
    }

    public Page setFenye(boolean fenye) {
        this.fenye = fenye;
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
