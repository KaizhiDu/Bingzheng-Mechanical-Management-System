package cn.jeeweb.modules.jygl.dto;
/**
 * Dscription: 检验管理 - 包工检验DTO
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/1 10:45
 */
public class BgjyDTO {
    /**包工明细ID*/
    private String id;
    /**包工任务分配ID*/
    private String bgrwfpid;
    /**日期*/
    private String rq;
    /**姓名*/
    private String xm;
    /**职位*/
    private String zw;
    /**承包金额*/
    private String cbje;
    /**注释*/
    private String zs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBgrwfpid() {
        return bgrwfpid;
    }

    public void setBgrwfpid(String bgrwfpid) {
        this.bgrwfpid = bgrwfpid;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
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

    public String getCbje() {
        return cbje;
    }

    public void setCbje(String cbje) {
        this.cbje = cbje;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }
}
