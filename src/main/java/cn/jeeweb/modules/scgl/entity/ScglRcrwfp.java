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
 * Dscription: 生产管理-日常任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 17:04
 */
@TableName("T_SCGL_RCRWFP")
@SuppressWarnings("serial")
public class ScglRcrwfp extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 员工ID */
    @TableField(value = "ygid")
    private String ygid;
    /** 日期 */
    @TableField(value = "RQ")
    private String rq;
    /** 姓名 */
    @TableField(value = "XM")
    private String xm;
    /** 职位 */
    @TableField(value = "ZW")
    private String zw;
    /** 性别 */
    @TableField(value = "XB")
    private String xb;
    /** 总工时 */
    @TableField(value = "GS")
    private String gs;
    /** 包工占用 */
    @TableField(value = "BGZY")
    private String bgzy;
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

    public String getBgzy() {
        return bgzy;
    }

    public void setBgzy(String bgzy) {
        this.bgzy = bgzy;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getRq() {
        return rq;
    }

    public void setRq(String rq) {
        this.rq = rq;
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

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
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

    public String getYgid() {
        return ygid;
    }

    public void setYgid(String ygid) {
        this.ygid = ygid;
    }

    public String getGs() {
        return gs;
    }

    public void setGs(String gs) {
        this.gs = gs;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
