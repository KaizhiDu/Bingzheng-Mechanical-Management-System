package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.BgsbDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgsb;
/**
 * Dscription: 包工 - 包工设备
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/26 15:52
 */
public interface IScglBgsbService extends ICommonService<ScglBgsb> {

    /**
     * Dscription: 包工 - 添加的设备展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 16:15
     */
    public PageJson<BgsbDTO> ajaxBgrwfpSbList(Queryable queryable, BgsbDTO rgsbDTO);
}
