package cn.jeeweb.modules.scjhgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;

/**
 * @Description:    生产计划管理-零件管理
 * @Author:         杜凯之
 * @CreateDate:     2018/9/4 17:07
 * @Version:        1.0
 */
public interface IScjhglLjglService extends ICommonService<ScjhglLjgl> {
    /**
     * @Description:    展示所有零件信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    public PageJson<ScjhglLjgl> ajaxljglList(Queryable queryable, ScjhglLjgl scjhglLjgl);
}
