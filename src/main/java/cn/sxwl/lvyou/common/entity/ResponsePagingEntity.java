package cn.sxwl.lvyou.common.entity;

public class ResponsePagingEntity {
    private Integer pageNum;
    private Object data;

    @Override
    public String toString() {
        return "ResponsePagingEntity{" +
                "pageNum=" + pageNum +
                ", data=" + data +
                '}';
    }

    public ResponsePagingEntity(Integer pageNum, Object data) {
        this.pageNum = pageNum;
        this.data = data;
    }

    public ResponsePagingEntity() {
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
