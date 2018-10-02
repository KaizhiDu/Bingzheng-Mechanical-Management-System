package cn.jeeweb.modules.grgl.dto;
/**
 * Dscription: 员工考勤记录DTO
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/2 16:55
 */
public class YgkqjlDTO {
    private String id;
    private String ygid;
    private String rq;
    private String name;
    private String gender;
    private String sw;
    private String xw;
    private String qqyy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYgid() {
        return ygid;
    }

    public void setYgid(String ygid) {
        this.ygid = ygid;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSw() {
        return sw;
    }

    public void setSw(String sw) {
        this.sw = sw;
    }

    public String getXw() {
        return xw;
    }

    public void setXw(String xw) {
        this.xw = xw;
    }

    public String getQqyy() {
        return qqyy;
    }

    public void setQqyy(String qqyy) {
        this.qqyy = qqyy;
    }
}
