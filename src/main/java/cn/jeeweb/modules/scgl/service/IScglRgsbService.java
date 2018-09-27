package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.RgsbDTO;
import cn.jeeweb.modules.scgl.entity.ScglRgsb;

/**
 * Dscription: 日工 - 日工设备
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/26 15:52
 */
public interface IScglRgsbService extends ICommonService<ScglRgsb> {
    /**
     * Dscription: 日工 - 添加的设备展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 16:15
     */
    public PageJson<RgsbDTO> ajaxRcrwfpSbList(Queryable queryable, RgsbDTO rgsbDTO);
}
