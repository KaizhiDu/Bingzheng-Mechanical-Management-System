package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglGj;

/**
 * Dscription: 仓库管理 - 工具
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 19:03
 */
public interface ICkglGjService extends ICommonService<CkglGj>{
    /**
     * Dscription: 展示所有工具的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public PageJson<CkglGj> ajaxGjList(Queryable queryable, CkglGj ckglGj);

}
