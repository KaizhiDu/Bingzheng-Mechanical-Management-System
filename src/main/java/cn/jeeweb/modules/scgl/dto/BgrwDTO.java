package cn.jeeweb.modules.scgl.dto;
/**
 * Dscription: 包工 - 包工任务DTO
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/28 14:55
 */
public class BgrwDTO {
    /**日工任务ID*/
    private String id;
    /**计划名称*/
    private String jhbh;
    /**零部件名称*/
    private String ljmc;
    /**工艺大类名称*/
    private String gydlmc;
    /**工艺小类名称*/
    private String gyxlmc;
    /**应完成量*/
    private String ywcl;
    /**实际完成量*/
    private String sjwcl;
    /**数量*/
    private String sl;
    /**剩余数量*/
    private String sysl;
    /**数量*/
    private int px;
    /**分配设备ID*/
    private String fpsbid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getYwcl() {
        return ywcl;
    }

    public void setYwcl(String ywcl) {
        this.ywcl = ywcl;
    }

    public String getSjwcl() {
        return sjwcl;
    }

    public void setSjwcl(String sjwcl) {
        this.sjwcl = sjwcl;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getSysl() {
        return sysl;
    }

    public void setSysl(String sysl) {
        this.sysl = sysl;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public String getFpsbid() {
        return fpsbid;
    }

    public void setFpsbid(String fpsbid) {
        this.fpsbid = fpsbid;
    }
}
