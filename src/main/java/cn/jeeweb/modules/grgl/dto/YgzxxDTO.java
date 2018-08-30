package cn.jeeweb.modules.grgl.dto;
/**
* @Description:    员工总信息
* @Author:         杜凯之
* @CreateDate:     2018/8/28 14:58
* @Version:        1.0
*/
public class YgzxxDTO {
    /**薪资职位分配ID*/
    private String id;
    /**员工姓名*/
    private String name;
    /**职务*/
    private String zw;
    /**职务工资*/
    private String zwgz;
    /**底薪*/
    private String dx;
    /**时薪*/
    private String sx;
    /**房补*/
    private String fb;
    /**交通费*/
    private String jtf;
    /**补贴*/
    private String bt;
    /**保险*/
    private String bx;
    /**扣款*/
    private String kk;
    /**不敢起名*/
    private String bgqm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public String getSx() {
        return sx;
    }

    public void setSx(String sx) {
        this.sx = sx;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getBx() {
        return bx;
    }

    public void setBx(String bx) {
        this.bx = bx;
    }

    public String getZwgz() {
        return zwgz;
    }

    public void setZwgz(String zwgz) {
        this.zwgz = zwgz;
    }

    public String getJtf() {
        return jtf;
    }

    public void setJtf(String jtf) {
        this.jtf = jtf;
    }

    public String getKk() {
        return kk;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }

    public String getBgqm() {
        return bgqm;
    }

    public void setBgqm(String bgqm) {
        this.bgqm = bgqm;
    }
}
