package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.BgrwDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgrw;

import java.util.List;

/**
 * Dscription: 包工 - 任务
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/27 14:01
 */
public interface IScglBgrwService extends ICommonService<ScglBgrw> {
    /**
     * Dscription: 展示所有包工任务
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 14:33
     */
    public PageJson<BgrwDTO> ajaxBgrwfpRwList(Queryable queryable, BgrwDTO bgrwDTO);

    /**
     * Dscription: 根据包工任务ID，获取所有包工任务
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/10 11:21
     */
    public List<ScglBgrw> getBgrwByBgrwfpid(String bgrwfpid);
}
