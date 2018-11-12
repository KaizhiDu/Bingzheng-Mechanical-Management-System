package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglBzjMx;

/**
 * Dscription: 仓库管理 - 标准件明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 13:30
 */
public interface ICkglBzjMxSevice extends ICommonService<CkglBzjMx> {

    public PageJson<CkglBzjMx> ajaxXqList(Queryable queryable, CkglBzjMx ckglBzjMx);
}
