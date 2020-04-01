package cn.jeeweb.modules.htgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglHtgl;
/**
 * Dscription: 合同管理 - 合同管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/3/21 16:40
 */

public interface IHtglHtglService extends ICommonService<HtglHtgl> {

    public PageJson<HtglHtgl> ajaxHtglList(Queryable queryable, HtglHtgl htglHtgl);

}
