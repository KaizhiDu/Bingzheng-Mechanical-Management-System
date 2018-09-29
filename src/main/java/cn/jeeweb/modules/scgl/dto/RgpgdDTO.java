package cn.jeeweb.modules.scgl.dto;

import java.util.List;

/**
 * Dscription: 日工派工单DTO
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/29 13:52
 */
public class RgpgdDTO {
    /**ID*/
    private String id;
    /**姓名*/
    private String xm;
    /**职位*/
    private String zw;
    /**日期*/
    private String rq;
    /**工时*/
    private String gs;
    /**工时名称*/
    private String gsmc;
    /**加班*/
    private String jb;
    /**内容*/
    private String nr;

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

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }

    public String getJb() {
        return jb;
    }

    public void setJb(String jb) {
        this.jb = jb;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getGsmc() {
        return gsmc;
    }

    public void setGsmc(String gsmc) {
        this.gsmc = gsmc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
