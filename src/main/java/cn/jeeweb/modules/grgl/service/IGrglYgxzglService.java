package cn.jeeweb.modules.grgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;

/**
 * Dscription: 员工管理 - 员工薪资管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/3 12:25
 */
public interface IGrglYgxzglService extends ICommonService<GrglYgxzgl> {

    /**
     * Dscription: 计算日工工资和工资总和
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/5 19:33
     */
    public void countGz(GrglYgxzgl grglYgxzgl);

    /**
     * Dscription: 得到所有员工薪资管理数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/6 17:56
     */
    public PageJson<GrglYgxzgl> ajaxYgxzglList(Queryable queryable, GrglYgxzgl grglYgxzgl);
}
