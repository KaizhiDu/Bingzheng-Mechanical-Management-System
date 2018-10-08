package cn.jeeweb.modules.scjhgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBjgl;

/**
 * Dscription: 生产计划管理 - 部件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/8 13:06
 */
public interface IScjhglBjglService extends ICommonService<ScjhglBjgl> {

    /**
     * Dscription: 展示所有部件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/8 17:06
     */
    public PageJson<ScjhglBjgl> ajaxBjglList(Queryable queryable, ScjhglBjgl scjhglBjgl);
}
