package cn.jeeweb.modules.grgl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 员工管理 - 员工薪资管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/3 12:25
 */

@TableName("T_GRGL_YGXZGL")
@SuppressWarnings("serial")
public class GrglYgxzgl extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 年度 */
    @TableField(value = "ND")
    private int nd;
    /** 月份 */
    @TableField(value = "YF")
    private int yf;
    /** 员工ID */
    @TableField(value = "YGID")
    private String ygid;
    /** 姓名 */
    @TableField(value = "XM")
    private String xm;
    /** 职位 */
    @TableField(value = "ZW")
    private String zw;
    /** 职位工资 */
    @TableField(value = "ZWGZ")
    private String zwgz;
    /** 底薪 */
    @TableField(value = "DX")
    private String dx;
    /** 房补 */
    @TableField(value = "FB")
    private String fb;
    /** 交通费 */
    @TableField(value = "JTF")
    private String jtf;
    /** 补贴 */
    @TableField(value = "BT")
    private String bt;
    /** 保险 */
    @TableField(value = "BX")
    private String bx;
    /** 出勤 */
    @TableField(value = "CQ")
    private String cq;
    /** 出勤工资 */
    @TableField(value = "CQGZ")
    private String cqgz;
    /** 夜班费 */
    @TableField(value = "CQGZ2")
    private String cqgz2;
    /** 总出勤工资 */
    @TableField(value = "ZCQGZ")
    private String zcqgz;
    /** 时薪 */
    @TableField(value = "SX")
    private String sx;
    /** 工时 */
    @TableField(value = "GS")
    private String gs;
    /** 日工工资 */
    @TableField(value = "RGGZ")
    private String rggz;
    /** 承包金额 */
    @TableField(value = "CBJE")
    private String cbje;
    /** 奖励 */
    @TableField(value = "JL")
    private String jl;
    /** 扣款 */
    @TableField(value = "KK")
    private String kk;
    /** 备注 */
    @TableField(value = "BZ")
    private String bz;
    /** 合计 */
    @TableField(value = "HJ")
    private String hj;
    /** 中夜班费 */
    @TableField(value = "zybf")
    private String zybf;

    /** 更新者 */
    @TableField(value = "update_by", el = "updateBy.id", fill = FieldFill.UPDATE)
    private User updateBy;
    /** 创建时间 */
    @TableField(value = "create_date", fill = FieldFill.UPDATE)
    private Date createDate;
    /** 创建者 */
    @TableField(value = "create_by", el = "createBy.id", fill = FieldFill.INSERT)
    private User createBy;
    /** 更新时间 */
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNd() {
        return nd;
    }

    public void setNd(int nd) {
        this.nd = nd;
    }

    public int getYf() {
        return yf;
    }

    public void setYf(int yf) {
        this.yf = yf;
    }

    public String getYgid() {
        return ygid;
    }

    public void setYgid(String ygid) {
        this.ygid = ygid;
    }

    public String getCq() {
        return cq;
    }

    public void setCq(String cq) {
        this.cq = cq;
    }

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }

    public String getCbje() {
        return cbje;
    }

    public void setCbje(String cbje) {
        this.cbje = cbje;
    }

    public String getJl() {
        return jl;
    }

    public void setJl(String jl) {
        this.jl = jl;
    }

    public String getKk() {
        return kk;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public User getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(User updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public String getZwgz() {
        return zwgz;
    }

    public void setZwgz(String zwgz) {
        this.zwgz = zwgz;
    }

    public String getDx() {
        return dx;
    }

    public void setDx(String dx) {
        this.dx = dx;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getJtf() {
        return jtf;
    }

    public void setJtf(String jtf) {
        this.jtf = jtf;
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

    public String getSx() {
        return sx;
    }

    public void setSx(String sx) {
        this.sx = sx;
    }

    public String getRggz() {
        return rggz;
    }

    public void setRggz(String rggz) {
        this.rggz = rggz;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getCqgz() {
        return cqgz;
    }

    public void setCqgz(String cqgz) {
        this.cqgz = cqgz;
    }

    public String getCqgz2() {
        return cqgz2;
    }

    public void setCqgz2(String cqgz2) {
        this.cqgz2 = cqgz2;
    }

    public String getZcqgz() {
        return zcqgz;
    }

    public void setZcqgz(String zcqgz) {
        this.zcqgz = zcqgz;
    }

    public String getZybf() {
        return zybf;
    }

    public void setZybf(String zybf) {
        this.zybf = zybf;
    }
}
