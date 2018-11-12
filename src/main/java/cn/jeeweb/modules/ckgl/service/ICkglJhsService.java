package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglJhs;

/**
 * Dscription: 仓库管理 - 进货商
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/11/12 13:02
 */
public interface ICkglJhsService extends ICommonService<CkglJhs>{

    public PageJson<CkglJhs> ajaxJhsList(Queryable queryable, CkglJhs ckglJhs);
}
