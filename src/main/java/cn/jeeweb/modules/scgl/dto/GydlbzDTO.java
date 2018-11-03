package cn.jeeweb.modules.scgl.dto;

public class GydlbzDTO {
    /**主键ID*/
    private String id;
    /**零部件ID*/
    private String ljid;
    /**零部件名称*/
    private String ljmc;
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
