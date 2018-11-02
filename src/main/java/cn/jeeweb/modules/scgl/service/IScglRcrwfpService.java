package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.RgpgJcxxDTO;
import cn.jeeweb.modules.scgl.dto.RgpgdDTO;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.ScglRcrwfp;

import java.util.List;

/**
 * Dscription: 生产管理-日常任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 17:04
 */
public interface IScglRcrwfpService extends ICommonService<ScglRcrwfp> {

    /**
     * Dscription: 得到员工数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:22
     */
    public List<YgsjDTO> getYgsj();

    /**
     * Dscription: 得到所有当天的员工的日常任务分配
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    public PageJson<ScglRcrwfp> ajaxRcrwfpList(Queryable queryable, ScglRcrwfp scglRcrwfp);

    /**
     * Dscription: 获取派工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/29 14:00
     */
    public List<RgpgJcxxDTO> getRgpgJcxx(String rq);

    /**
     * Dscription: 获取最终派工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/29 14:00
     */
    public List<RgpgdDTO> getRgpgd(String rq);
}
