package cn.jeeweb.modules.jygl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.jygl.dto.RgjyDTO;
import cn.jeeweb.modules.jygl.entity.JyglRgjy;

import java.util.List;

/**
 * Dscription: 检验管理 - 日工检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/30 15:36
 */
public interface IJyglRgjyService extends ICommonService<JyglRgjy> {

    /**
     * Dscription: 展示所有检验信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 17:13
     */
    public PageJson<JyglRgjy> ajaxRgjyList(Queryable queryable, RgjyDTO rgjyDTO);

    /**
     * Dscription: 导出日工检验派工单
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/8 10:46
     */
    public List<RgjyDTO> exportJypgd(String xm, String rq);
}
