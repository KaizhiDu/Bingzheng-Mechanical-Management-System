package cn.jeeweb.modules.jygl.entity;

import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

@TableName("T_JYGL_RGJL")
@SuppressWarnings("serial")
public class JyglRgjl {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    @TableField(value = "XM")
    private String xm;
    /** 应完成量 */
    @TableField(value = "YWCL")
    private String ywcl;
    /** 每天任务量 */
    @TableField(value = "MTRWL")
    private String mtrwl;
    /** 实际完成量 */
    @TableField(value = "SJWCL")
    private String sjwcl;
    /** 报废量 */
    @TableField(value = "BFL")
    private String bfl;
    @TableField(value = "cjrq")
    private String cjrq;
    @TableField(value = "jyrq")
    private String jyrq;
    @TableField(value = "mc")
    private String mc;
    @TableField(value = "sbmc")
    private String sbmc;
    @TableField(value = "jhbh")
    private String jhbh;
    @TableField(value = "ljmc")
    private String ljmc;
    @TableField(value = "ljth")
    private String ljth;
    @TableField(value = "gydlmc")
    private String gydlmc;
    @TableField(value = "gyxlmc")
    private String gyxlmc;
    @TableField(value = "zs")
    private String zs;

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

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getYwcl() {
        return ywcl;
    }

    public void setYwcl(String ywcl) {
        this.ywcl = ywcl;
    }

    public String getMtrwl() {
        return mtrwl;
    }

    public void setMtrwl(String mtrwl) {
        this.mtrwl = mtrwl;
    }

    public String getSjwcl() {
        return sjwcl;
    }

    public void setSjwcl(String sjwcl) {
        this.sjwcl = sjwcl;
    }

    public String getBfl() {
        return bfl;
    }

    public void setBfl(String bfl) {
        this.bfl = bfl;
    }

    public String getCjrq() {
        return cjrq;
    }

    public void setCjrq(String cjrq) {
        this.cjrq = cjrq;
    }

    public String getJyrq() {
        return jyrq;
    }

    public void setJyrq(String jyrq) {
        this.jyrq = jyrq;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
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

    public String getLjth() {
        return ljth;
    }

    public void setLjth(String ljth) {
        this.ljth = ljth;
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

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }
}
