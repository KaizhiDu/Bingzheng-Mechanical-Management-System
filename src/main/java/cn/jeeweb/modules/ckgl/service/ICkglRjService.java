package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglRj;

/**
 * Dscription: 仓库管理 - 刃具
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 9:13
 */
public interface ICkglRjService extends ICommonService<CkglRj>{

    /**
     * Dscription: 展示所有刃具的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public PageJson<CkglRj> ajaxRjList(Queryable queryable, CkglRj ckglRj);
}
