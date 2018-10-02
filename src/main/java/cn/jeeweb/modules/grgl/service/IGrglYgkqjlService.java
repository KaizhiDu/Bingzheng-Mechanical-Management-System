package cn.jeeweb.modules.grgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.grgl.dto.YgkqjlDTO;
import cn.jeeweb.modules.grgl.entity.GrglYgkqjl;

/**
 * Dscription: 员工管理 - 员工考勤记录
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/2 14:32
 */
public interface IGrglYgkqjlService extends ICommonService<GrglYgkqjl> {

    /**
     * Dscription: 展示所有考勤信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:29
     */
    public PageJson<YgkqjlDTO> ajaxGrglYgkqjlList(Queryable queryable, YgkqjlDTO ygkqjlDTO);
}
