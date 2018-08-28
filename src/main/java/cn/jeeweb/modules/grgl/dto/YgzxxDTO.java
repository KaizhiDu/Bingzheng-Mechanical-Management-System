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
    /**底薪*/
    private String dx;
    /**时薪*/
    private String sx;
    /**餐补*/
    private String cb;
    /**房补*/
    private String fb;
    /**补贴*/
    private String bt;
    /**保险*/
    private String bx;

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

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
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
}
