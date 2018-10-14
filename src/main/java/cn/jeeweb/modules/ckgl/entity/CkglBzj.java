package cn.jeeweb.modules.ckgl.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 仓库管理 - 标准件
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/13 14:09
 */
@TableName("T_CKGL_BZJ")
@SuppressWarnings("serial")
public class CkglBzj extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 分类大类 */
    @TableField(value = "FLDL")
    private String fldl;
    /** 分类小类 */
    @TableField(value = "FLXL")
    private String flxl;
    /** 规格 */
    @TableField(value = "GG")
    private String gg;
    /** 单位 */
    @TableField(value = "DW")
    private String dw;
    /** 库存 */
    @TableField(value = "KC")
    private String kc;
    /** 预警库存 */
    @TableField(value = "YJKC")
    private String yjkc;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFldl() {
        return fldl;
    }

    public void setFldl(String fldl) {
        this.fldl = fldl;
    }

    public String getFlxl() {
        return flxl;
    }

    public void setFlxl(String flxl) {
        this.flxl = flxl;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getDw() {
        return dw;
    }

    public void setDw(String dw) {
        this.dw = dw;
    }

    public String getKc() {
        return kc;
    }

    public void setKc(String kc) {
        this.kc = kc;
    }

    public String getYjkc() {
        return yjkc;
    }

    public void setYjkc(String yjkc) {
        this.yjkc = yjkc;
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
}
