package cn.jeeweb.modules.htgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglWcqk;

public interface IHtglWcqkService extends ICommonService<HtglWcqk> {
    public PageJson<HtglWcqk> ajaxWcqkList(Queryable queryable, HtglWcqk htglWcqk);

}
