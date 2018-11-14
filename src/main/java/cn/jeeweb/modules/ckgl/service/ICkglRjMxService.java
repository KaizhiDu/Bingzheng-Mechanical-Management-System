package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglRjMx;

/**
 * Dscription: 仓库管理 - 刃具明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 9:22
 */
public interface ICkglRjMxService extends ICommonService<CkglRjMx>{
    public PageJson<CkglRjMx> ajaxXqList(Queryable queryable, CkglRjMx ckglRjMx);
}
