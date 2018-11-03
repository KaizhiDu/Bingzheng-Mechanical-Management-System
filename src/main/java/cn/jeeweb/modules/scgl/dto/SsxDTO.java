package cn.jeeweb.modules.scgl.dto;

/**
 * Dscription: 搜索项DTO
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 12:39
 */
public class SsxDTO {
    /**计划ID*/
    private String jhid;
    /**计划名称*/
    private String jhbh;
    /**零部件ID*/
    private String ljid;
    /**零部件名称*/
    private String ljmc;
    /**工艺大类编制ID*/
    private String gydlbzid;
    /**工艺大类编制名称*/
    private String gydlmc;
    /**工艺小类编制ID*/
    private String gyxlid;
    /**工艺小类编制名称*/
    private String gyxlmc;

    public String getJhid() {
        return jhid;
    }

    public void setJhid(String jhid) {
        this.jhid = jhid;
    }

    public String getJhbh() {
        return jhbh;
    }

    public void setJhbh(String jhbh) {
        this.jhbh = jhbh;
    }

    public String getLjid() {
        return ljid;
    }

    public void setLjid(String ljid) {
        this.ljid = ljid;
    }

    public String getLjmc() {
        return ljmc;
    }

    public void setLjmc(String ljmc) {
        this.ljmc = ljmc;
    }

    public String getGydlbzid() {
        return gydlbzid;
    }

    public void setGydlbzid(String gydlbzid) {
        this.gydlbzid = gydlbzid;
    }

    public String getGydlmc() {
        return gydlmc;
    }

    public void setGydlmc(String gydlmc) {
        this.gydlmc = gydlmc;
    }

    public String getGyxlid() {
        return gyxlid;
    }

    public void setGyxlid(String gyxlid) {
        this.gyxlid = gyxlid;
    }

    public String getGyxlmc() {
        return gyxlmc;
    }

    public void setGyxlmc(String gyxlmc) {
        this.gyxlmc = gyxlmc;
    }
}
