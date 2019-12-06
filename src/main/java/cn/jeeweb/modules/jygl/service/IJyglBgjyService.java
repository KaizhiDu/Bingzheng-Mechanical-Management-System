package cn.jeeweb.modules.jygl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.jygl.dto.BgjyDTO;
import cn.jeeweb.modules.jygl.dto.BgjyxqDTO;
import cn.jeeweb.modules.jygl.entity.JyglBgjy;
import cn.jeeweb.modules.jygl.entity.JyglRgjl;

import java.util.List;

/**
 * Dscription: 检验管理 - 包工检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/1 10:54
 */
public interface IJyglBgjyService extends ICommonService<JyglBgjy> {

    /**
     * Dscription: 展示所有包工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:14
     */
    public PageJson<BgjyDTO> ajaxBgjyList(Queryable queryable, BgjyDTO bgjyDTO);

    public PageJson<JyglRgjl> ajaxRgjl(Queryable queryable, JyglRgjl jyglRgjl);

    public List<BgjyDTO> exportBgjyd(String xm, String rq, String bgrg);

    /**
     * Dscription: 展示所有包工详情信息（展示）
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:14
     */
    public PageJson<BgjyxqDTO> ajaxBgjyxqList(Queryable queryable, BgjyxqDTO bgjyxqDTO, String id);

    /** 展示所有包工详情信息（数据处理）
     * Dscription:
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 12:25
     */
    public List<BgjyxqDTO> bgjyxqList(BgjyxqDTO bgjyxqDTO, String id);
}
