package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.BgpgJcxxDTO;
import cn.jeeweb.modules.scgl.dto.BgpgdDTO;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgrwfp;

import java.util.List;

/**
 * Dscription: 生产管理-包工任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/28 12:19
 */
public interface IScglBgrwfpService extends ICommonService<ScglBgrwfp> {
    /**
     * Dscription: 得到员工数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:22
     */
    public List<YgsjDTO> getYgsj();

    /**
     * Dscription: 得到所有当天的员工的包工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    public PageJson<ScglBgrwfp> ajaxBgrwfpList(Queryable queryable, ScglBgrwfp scglBgrwfp);

    /**
     * Dscription: 得到包工派工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 10:50
     */
    public List<BgpgdDTO> getBgpgd();

    /**
     * Dscription: 得到包工派工基础数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 10:55
     */
    public List<BgpgJcxxDTO> getBgpgJcxx();
}
