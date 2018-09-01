package cn.jeeweb.modules.sbgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.sbgl.entity.Sbgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbrwjk;

/**
 * @Description:    设备管理-设备任务监控
 * @Author:         杜凯之
 * @CreateDate:     2018/9/1 13:49
 * @Version:        1.0
 */
public interface ISbglSbrwjkService extends ICommonService<SbglSbrwjk> {
    /**
     * @Description:    展示所有设备监控情况
     * @Author:         杜凯之
     * @CreateDate:     2018/8/30 17:58
     * @Version:        1.0
     */
    public PageJson<SbglSbrwjk> ajaxListSbrwjk(Queryable queryable, SbglSbrwjk sbglSbrwjk);
}
