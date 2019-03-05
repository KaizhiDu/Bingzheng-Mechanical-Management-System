package cn.jeeweb.modules.zjls.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.zjls.entity.ZjlsZjls;

/**
 * Dscription: 资金流水
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/2/17 18:36
 */

public interface ZjlsZjlsService extends ICommonService<ZjlsZjls>{
    public PageJson<ZjlsZjls> ajaxZjlsList(Queryable queryable, ZjlsZjls zjlsZjls);
}
