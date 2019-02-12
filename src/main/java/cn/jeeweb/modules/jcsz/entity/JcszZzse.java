package cn.jeeweb.modules.jcsz.entity;

import cn.jeeweb.core.common.entity.AbstractEntity;
import cn.jeeweb.modules.sys.entity.User;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

/**
 * Dscription: 基础数据 - 资金数额
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/3 15:38
 */
@TableName("T_JCSZ_ZZSE")
@SuppressWarnings("serial")
public class JcszZzse extends AbstractEntity<String> {
    /** 主键 */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;

    /** 1 */
    @TableField(value = "ONE")
    private String one;
    /** 2 */
    @TableField(value = "TWO")
    private String two;
    /** 3 */
    @TableField(value = "THREE")
    private String three;
    /** 4 */
    @TableField(value = "FOUR")
    private String four;
    /** 5 */
    @TableField(value = "FIVE")
    private String five;
    /** 6 */
    @TableField(value = "SIX")
    private String six;
    /** 7 */
    @TableField(value = "SEVEN")
    private String seven;
    /** 8 */
    @TableField(value = "EIGHT")
    private String eight;
    /** 9 */
    @TableField(value = "NINE")
    private String nine;
    /** 10 */
    @TableField(value = "TEN")
    private String ten;
    /** 11 */
    @TableField(value = "ELEVEN")
    private String eleven;
    /** 12 */
    @TableField(value = "TWELVE")
    private String twelve;
    /** 13 */
    @TableField(value = "THIRTEEN")
    private String thirteen;
    /** 14 */
    @TableField(value = "FOURTEEN")
    private String fourteen;
    /** 15 */
    @TableField(value = "FIFTEEN")
    private String fifteen;
    /** 16 */
    @TableField(value = "SIXTEEN")
    private String sixteen;
    /** 17 */
    @TableField(value = "SEVENTEEN")
    private String seventeen;
    /** 18 */
    @TableField(value = "EIGHTEEN")
    private String eighteen;
    /** 19 */
    @TableField(value = "NINETEEN")
    private String nineteen;
    /** 20 */
    @TableField(value = "TWENTY")
    private String twenty;
    /** 类型 */
    @TableField(value = "TYPE")
    private String type;

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

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getFive() {
        return five;
    }

    public void setFive(String five) {
        this.five = five;
    }

    public String getSix() {
        return six;
    }

    public void setSix(String six) {
        this.six = six;
    }

    public String getSeven() {
        return seven;
    }

    public void setSeven(String seven) {
        this.seven = seven;
    }

    public String getEight() {
        return eight;
    }

    public void setEight(String eight) {
        this.eight = eight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getNine() {
        return nine;
    }

    public void setNine(String nine) {
        this.nine = nine;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEleven() {
        return eleven;
    }

    public void setEleven(String eleven) {
        this.eleven = eleven;
    }

    public String getTwelve() {
        return twelve;
    }

    public void setTwelve(String twelve) {
        this.twelve = twelve;
    }

    public String getThirteen() {
        return thirteen;
    }

    public void setThirteen(String thirteen) {
        this.thirteen = thirteen;
    }

    public String getFourteen() {
        return fourteen;
    }

    public void setFourteen(String fourteen) {
        this.fourteen = fourteen;
    }

    public String getFifteen() {
        return fifteen;
    }

    public void setFifteen(String fifteen) {
        this.fifteen = fifteen;
    }

    public String getSixteen() {
        return sixteen;
    }

    public void setSixteen(String sixteen) {
        this.sixteen = sixteen;
    }

    public String getSeventeen() {
        return seventeen;
    }

    public void setSeventeen(String seventeen) {
        this.seventeen = seventeen;
    }

    public String getEighteen() {
        return eighteen;
    }

    public void setEighteen(String eighteen) {
        this.eighteen = eighteen;
    }

    public String getNineteen() {
        return nineteen;
    }

    public void setNineteen(String nineteen) {
        this.nineteen = nineteen;
    }

    public String getTwenty() {
        return twenty;
    }

    public void setTwenty(String twenty) {
        this.twenty = twenty;
    }
}
