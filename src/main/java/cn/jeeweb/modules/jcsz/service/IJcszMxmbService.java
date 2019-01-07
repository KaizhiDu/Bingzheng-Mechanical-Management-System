package cn.jeeweb.modules.jcsz.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.jcsz.entity.JcszMxmb;

public interface IJcszMxmbService extends ICommonService<JcszMxmb>{
    /**
     * Dscription: 展示所有明细模板信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/4 15:31
     */
    public PageJson<JcszMxmb> ajaxMxmbList(Queryable queryable, JcszMxmb jcszMxmb);
}
