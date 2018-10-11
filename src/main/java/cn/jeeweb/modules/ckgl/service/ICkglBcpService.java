package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;

/**
 * Dscription: 仓库管理 - 未完成半成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 13:24
 */
public interface ICkglBcpService extends ICommonService<CkglBcp> {

    /**
     * Dscription: 查所有已完成或者未完成的半成品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:17
     */
    public PageJson<CkglBcp> ajaxBcpList(Queryable queryable, CkglBcp ckglBcp);

    /**
     * Dscription: 查所有未完成的半成品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:17
     */
    public PageJson<CkglBcp> ajaxBcpList2(Queryable queryable, CkglBcp ckglBcp);
}
