package cn.jeeweb.modules.htglold.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htglold.entity.HtglHt;

/**
 * Dscription: 合同管理 - 合同
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/10 13:37
 */
public interface IHtglHtService extends ICommonService<HtglHt>{

    public PageJson<HtglHt> ajaxHtList(Queryable queryable, HtglHt htglHt);

}
