package cn.jeeweb.modules.scjhgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;

/**
 * @Description:    生产计划管理-合同管理
 * @Author:         杜凯之
 * @CreateDate:     2018/9/12 14:22
 * @Version:        1.0
 */
public interface IScjhglHtglService extends ICommonService<ScjhglHtgl> {

    /**
     * @Description:    展示所有计划信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/14 17:01
     * @Version:        1.0
     */
    public PageJson<ScjhglHtgl> ajaxJhglList(Queryable queryable, ScjhglHtgl scjhglHtgl);

}
