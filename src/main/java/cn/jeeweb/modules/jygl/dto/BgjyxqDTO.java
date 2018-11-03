package cn.jeeweb.modules.jygl.dto;
/**
 * Dscription: 检验管理 - 包工检验详情DTO
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/1 10:45
 */
public class BgjyxqDTO {
    /**主键ID*/
    private String id;
    /**姓名*/
    private String xm;
    /**职位*/
    private String zw;
    /**日期*/
    private String rq;
    /**计划名称*/
    private String jhbh;
    /**零部件名称*/
    private String ljmc;
    /**工艺大类名称*/
    private String gydlmc;
    /**工艺小类名称*/
    private String gyxlmc;
    /**设备名称*/
    private String sbmc;
    /**应完成量*/
    private String ywcl;
    /**零部件工艺编制ID*/
    private String ljgybzid;

    public String getLjgybzid() {
        return ljgybzid;
    }

    public void setLjgybzid(String ljgybzid) {
        this.ljgybzid = ljgybzid;
    }

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

    public String getJhbh() {
        return jhbh;
    }

    public void setJhbh(String jhbh) {
        this.jhbh = jhbh;
    }

    public String getLjmc() {
        return ljmc;
    }

    public void setLjmc(String ljmc) {
        this.ljmc = ljmc;
    }

    public String getGydlmc() {
        return gydlmc;
    }

    public void setGydlmc(String gydlmc) {
        this.gydlmc = gydlmc;
    }

    public String getGyxlmc() {
        return gyxlmc;
    }

    public void setGyxlmc(String gyxlmc) {
        this.gyxlmc = gyxlmc;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public String getYwcl() {
        return ywcl;
    }

    public void setYwcl(String ywcl) {
        this.ywcl = ywcl;
    }
}
