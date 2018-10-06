package cn.jeeweb.modules.grgl.service;

import cn.jeeweb.core.common.service.ICommonService;
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
}
