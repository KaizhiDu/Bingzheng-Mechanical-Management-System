package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.entity.ScglSzgyxl;

/**
 * @Description:    生产管理-设置工艺小类
 * @Author:         杜凯之
 * @CreateDate:     2018/9/11 16:47
 * @Version:        1.0
 */
public interface IScglSzgyxlService extends ICommonService<ScglSzgyxl> {
    /**
     * @Description:    显示设置工艺小类
     * @Author:         杜凯之
     * @CreateDate:     2018/9/11 18:45
     * @Version:        1.0
     */
    public PageJson<ScglSzgyxl> szgyxlList(Queryable queryable, ScglSzgyxl scglSzgyxl);
}
