package cn.jeeweb.modules.htgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglHtmx;

/**
 * Dscription: 合同管理 - 合同明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/11 13:40
 */
public interface IHtglHtmxService extends ICommonService<HtglHtmx>{

    public PageJson<HtglHtmx> ajaxHtmxList(Queryable queryable, HtglHtmx htglHtmx);
}
