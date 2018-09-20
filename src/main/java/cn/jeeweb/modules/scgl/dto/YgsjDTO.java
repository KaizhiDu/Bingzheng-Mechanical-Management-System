package cn.jeeweb.modules.scgl.dto;
/**
 * Dscription: 得到员工数据
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 18:20
 */
public class YgsjDTO {
    /**姓名*/
    private String xm;
    /**职务*/
    private String zw;
    /**性别*/
    private String xb;
    /**日期*/
    private String rq;

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }
}
