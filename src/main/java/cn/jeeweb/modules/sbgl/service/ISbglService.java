package cn.jeeweb.modules.sbgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.sbgl.entity.Sbgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;

/**
 * @Description:    设备管理
 * @Author:         杜凯之
 * @CreateDate:     2018/8/30 17:00
 * @Version:        1.0
 */
public interface ISbglService extends ICommonService<Sbgl>{
    /**
     * @Description:    展示所有设备
     * @Author:         杜凯之
     * @CreateDate:     2018/8/30 17:58
     * @Version:        1.0
     */
    public PageJson<Sbgl> ajaxListSbgl(Queryable queryable, Sbgl sbgl);
}
