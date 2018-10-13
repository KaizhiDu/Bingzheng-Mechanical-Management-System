package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglXl;

/**
 * Dscription: 仓库管理 - 小类
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/13 9:48
 */
public interface ICkglXlService extends ICommonService<CkglXl> {

    /**
     * Dscription: 根据大类id查到下属工艺小类的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 12:55
     */
    public PageJson<CkglXl> ajaxCkxlList(Queryable queryable, CkglXl ckglXl);
}
