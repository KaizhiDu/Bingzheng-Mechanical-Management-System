package cn.jeeweb.modules.htglold.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htglold.entity.HtglHtmx2;

/**
 * Dscription: 合同管理 - 合同明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/11 13:40
 */
public interface IHtglHtmxService2 extends ICommonService<HtglHtmx2>{

    public PageJson<HtglHtmx2> ajaxHtmxList(Queryable queryable, HtglHtmx2 htglHtmx2);
}
