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
 * @Description:    生产管理-零件工艺编制
 * @Author:         杜凯之
 * @CreateDate:     2018/9/14 15:58
 * @Version:        1.0
 */
@TableName("T_SCGL_LJGYBZ")
@SuppressWarnings("serial")
public class ScglGybzgl extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 工艺大类编制ID */
    @TableField(value = "GYDLBZID")
    private String gydlbzid;
    /** 工艺小类ID */
    @TableField(value = "GYXLID")
    private String gyxlid;
    /** 工艺小类名称 */
    @TableField(value = "GYXLMC")
    private String gyxlmc;
    /** 描述 */
    @TableField(value = "MS")
    private String ms;
    /** 排序 */
    @TableField(value = "PX")
    private int px;
    /** 数量 */
    @TableField(value = "SL")
    private int sl;

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

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getGydlbzid() {
        return gydlbzid;
    }

    public void setGydlbzid(String gydlbzid) {
        this.gydlbzid = gydlbzid;
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

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
}
