package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglYclMx;

/**
 * Dscription: 仓库管理 - 原材料明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 15:40
 */
public interface ICkglYclMxService extends ICommonService<CkglYclMx> {

    public PageJson<CkglYclMx> ajaxXqList(Queryable queryable, CkglYclMx ckglYclMx);
}
