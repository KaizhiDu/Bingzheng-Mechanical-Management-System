package cn.jeeweb.modules.zzgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.zzgl.entity.ZzglJh;

/**
 * Dscription: 资金管理 - 借还
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/7 14:47
 */
public interface IZzglJhService extends ICommonService<ZzglJh>{

    public PageJson<ZzglJh> ajaxJhList(Queryable queryable, ZzglJh zzglJh);
}
