package cn.jeeweb.modules.sbgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;

/**
 * @Description:    设备管理-设备分类管理
 * @Author:         杜凯之
 * @CreateDate:     2018/8/31 16:47
 * @Version:        1.0
 */
public interface ISbglSbflglService extends ICommonService<SbglSbflgl> {
    /**
     * @Description:    展示所有设备分类管理
     * @Author:         杜凯之
     * @CreateDate:     2018/8/31 17:29
     * @Version:        1.0
     */
    public PageJson<SbglSbflgl> sbglSbflglList(Queryable queryable, SbglSbflgl sbglSbflgl);
}
