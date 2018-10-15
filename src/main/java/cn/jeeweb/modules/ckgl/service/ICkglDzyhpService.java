package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglDzyhp;

/**
 * Dscription: 仓库管理 - 低值易耗品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 12:17
 */
public interface ICkglDzyhpService extends ICommonService<CkglDzyhp>{
    /**
     * Dscription: 保存低值易耗品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 16:59
     */
    public PageJson<CkglDzyhp> ajaxDzyhpList(Queryable queryable, CkglDzyhp ckglYcl);
}
