package cn.jeeweb.modules.htgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglFpxq;

public interface IHtglFpxqService extends ICommonService<HtglFpxq> {

    public PageJson<HtglFpxq> ajaxFpxqList(Queryable queryable, HtglFpxq htglFpxq);

}
