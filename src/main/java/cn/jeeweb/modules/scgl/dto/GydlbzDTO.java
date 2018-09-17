package cn.jeeweb.modules.scgl.dto;

public class GydlbzDTO {
    /**主键ID*/
    private String id;
    /**计划ID*/
    private String jhid;
    /**计划编号*/
    private String jhbh;
    /**工艺大类ID*/
    private String gydlid;
    /**工艺大类名称*/
    private String gydlmc;
    /**排序*/
    private String px;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getGydlid() {
        return gydlid;
    }

    public void setGydlid(String gydlid) {
        this.gydlid = gydlid;
    }

    public String getGydlmc() {
        return gydlmc;
    }

    public void setGydlmc(String gydlmc) {
        this.gydlmc = gydlmc;
    }

    public String getPx() {
        return px;
    }

    public void setPx(String px) {
        this.px = px;
    }
}
