package cn.jeeweb.modules.htgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglHkxq;

public interface IHtglHkxqService extends ICommonService<HtglHkxq> {

    public PageJson<HtglHkxq> ajaxHkxqList(Queryable queryable, HtglHkxq htglHkxq);

}
