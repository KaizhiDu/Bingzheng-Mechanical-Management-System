package cn.jeeweb.modules.htgl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 合同管理 - 合同管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/3/21 16:40
 */

@TableName("T_HTGL_HTGL")
@SuppressWarnings("serial")
public class HtglHtgl extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 合同号 */
    @TableField(value = "HTH")
    private String hth;
    /** 企业合同号 */
    @TableField(value = "QYHTH")
    private String qyhth;
    /** 名称 */
    @TableField(value = "MC")
    private String mc;
    /** 签订日期 */
    @TableField(value = "QDRQ")
    private String qdrq;
    /** 工期 */
    @TableField(value = "GQ")
    private String gq;
    /** 所属公司 */
    @TableField(value = "SSGS")
    private String ssgs;
    /** 金额 */
    @TableField(value = "JE")
    private Double je;
    /** 发票金额 */
    @TableField(value = "FP")
    private Double fp;
    /** 回款金额 */
    @TableField(value = "HK")
    private Double hk;
    /** 备注 */
    @TableField(value = "BZ")
    private String bz;

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


    public String getSsgs() {
        return ssgs;
    }

    public void setSsgs(String ssgs) {
        this.ssgs = ssgs;
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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getHth() {
        return hth;
    }

    public void setHth(String hth) {
        this.hth = hth;
    }

    public String getQyhth() {
        return qyhth;
    }

    public void setQyhth(String qyhth) {
        this.qyhth = qyhth;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getQdrq() {
        return qdrq;
    }

    public void setQdrq(String qdrq) {
        this.qdrq = qdrq;
    }

    public String getGq() {
        return gq;
    }

    public void setGq(String gq) {
        this.gq = gq;
    }

    public Double getJe() {
        return je;
    }

    public void setJe(Double je) {
        this.je = je;
    }

    public Double getFp() {
        return fp;
    }

    public void setFp(Double fp) {
        this.fp = fp;
    }

    public Double getHk() {
        return hk;
    }

    public void setHk(Double hk) {
        this.hk = hk;
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

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
