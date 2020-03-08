package cn.jeeweb.modules.scgl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 包工 - 任务
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/27 14:01
 */
@TableName("T_SCGL_BGRWFP_BG_RW")
@SuppressWarnings("serial")
public class ScglBgrw extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 零部件工艺编制ID */
    @TableField(value = "LJGYBZID")
    private String ljgybzid;
    /** 分配设备ID */
    @TableField(value = "BGRWFPID")
    private String bgrwfpid;
    /** 应完成量 */
    @TableField(value = "YWCL")
    private String ywcl;
    /** 每天任务量 */
    @TableField(value = "MTRWL")
    private String mtrwl;
    /** 实际完成量 */
    @TableField(value = "SJWCL")
    private String sjwcl;
    /** 是否未达标完成 */
    @TableField(value = "SFWDBWC")
    private String sfwdbwc;
    /** 报废量 */
    @TableField(value = "BFL")
    private String bfl;
    /** 排序 */
    @TableField(value = "PX")
    private int px;
    /** 注释 */
    @TableField(value = "ZS")
    private String zs;
    /** 单价 */
    @TableField(value = "DJ")
    private String dj;
    /** 剩余工序数量 */
    @TableField(value = "SYGXSL")
    private String sygxsl;
    /** 计划ID */
    @TableField(value = "JHID")
    private String jhid;

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

    public String getMtrwl() {
        return mtrwl;
    }

    public void setMtrwl(String mtrwl) {
        this.mtrwl = mtrwl;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLjgybzid() {
        return ljgybzid;
    }

    public void setLjgybzid(String ljgybzid) {
        this.ljgybzid = ljgybzid;
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

    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public String getBgrwfpid() {
        return bgrwfpid;
    }

    public void setBgrwfpid(String bgrwfpid) {
        this.bgrwfpid = bgrwfpid;
    }

    public String getBfl() {
        return bfl;
    }

    public void setBfl(String bfl) {
        this.bfl = bfl;
    }

    public String getSfwdbwc() {
        return sfwdbwc;
    }

    public void setSfwdbwc(String sfwdbwc) {
        this.sfwdbwc = sfwdbwc;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getSygxsl() {
        return sygxsl;
    }

    public void setSygxsl(String sygxsl) {
        this.sygxsl = sygxsl;
    }

    public String getJhid() {
        return jhid;
    }

    public void setJhid(String jhid) {
        this.jhid = jhid;
    }
}
