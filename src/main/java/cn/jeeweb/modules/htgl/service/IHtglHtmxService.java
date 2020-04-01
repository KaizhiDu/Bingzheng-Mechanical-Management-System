package cn.jeeweb.modules.htgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglHtmx;

public interface IHtglHtmxService extends ICommonService<HtglHtmx> {

    public PageJson<HtglHtmx> ajaxHtmxList(Queryable queryable, HtglHtmx htglHtmx);

}
