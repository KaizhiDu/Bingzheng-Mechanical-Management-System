package cn.jeeweb.modules.scgl.dto;
/**
 * Dscription: 包工派工单
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/30 10:43
 */
public class BgpgdDTO {
    /**ID*/
    private String id;
    /**姓名*/
    private String xm;
    /**职位*/
    private String zw;
    /**日期*/
    private String rq;
    /**注释*/
    private String zs;
    /**承包金额*/
    private String cbje;
    /**内容*/
    private String nr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getCbje() {
        return cbje;
    }

    public void setCbje(String cbje) {
        this.cbje = cbje;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }
}
