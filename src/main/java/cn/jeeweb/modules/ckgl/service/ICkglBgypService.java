package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglBgyp;

/**
 * Dscription: 仓库管理 - 办公用品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 10:28
 */
public interface ICkglBgypService extends ICommonService<CkglBgyp>{

    /**
     * Dscription: 展示所有办公用品的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public PageJson<CkglBgyp> ajaxBgypList(Queryable queryable, CkglBgyp ckglYcl);
}
