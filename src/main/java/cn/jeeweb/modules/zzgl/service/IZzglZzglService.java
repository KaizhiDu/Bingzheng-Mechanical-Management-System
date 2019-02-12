package cn.jeeweb.modules.zzgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.zzgl.dto.ZzglDTO;
import cn.jeeweb.modules.zzgl.entity.ZzglZzgl;

import java.util.List;

/**
 * Dscription: 资金管理 - 资金管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/5 9:33
 */
public interface IZzglZzglService extends ICommonService<ZzglZzgl>{
    /**
     * Dscription: 展示所有资金管理信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/5 14:47
     */
    public PageJson<ZzglZzgl> ajaxZzglList(Queryable queryable, ZzglDTO zzglDTO);

    /**
     * Dscription: 导出资金管理数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/7 22:17
     */
    public List<ZzglZzgl> exportZzgl(String n1, String y1, String r1, String n2, String y2, String r2);

}
