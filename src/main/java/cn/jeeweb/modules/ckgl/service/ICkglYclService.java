package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglYcl;

/**
 * Dscription: 仓库管理 - 原材料
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 15:09
 */
public interface ICkglYclService extends ICommonService<CkglYcl> {

    /**
     * Dscription: 展示所有原材料的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public PageJson<CkglYcl> ajaxYclList(Queryable queryable, CkglYcl ckglYcl);
}
