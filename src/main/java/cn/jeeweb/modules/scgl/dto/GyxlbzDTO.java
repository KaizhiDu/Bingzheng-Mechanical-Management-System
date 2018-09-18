package cn.jeeweb.modules.scgl.dto;

public class GyxlbzDTO {
    /**主键ID*/
    private String id;
    /**工艺大类编制ID*/
    private String gydlbzid;
    /**工艺大类编制名称*/
    private String gydlbzmc;
    /**工艺小类ID*/
    private String gyxlid;
    /**工艺小类名称*/
    private String gyxlmc;
    /**描述*/
    private String ms;
    /**排序*/
    private int px;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGydlbzid() {
        return gydlbzid;
    }

    public void setGydlbzid(String gydlbzid) {
        this.gydlbzid = gydlbzid;
    }

    public String getGydlbzmc() {
        return gydlbzmc;
    }

    public void setGydlbzmc(String gydlbzmc) {
        this.gydlbzmc = gydlbzmc;
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

    public String getMs() {
        return ms;
    }

    public void setMs(String ms) {
        this.ms = ms;
    }

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }
}
