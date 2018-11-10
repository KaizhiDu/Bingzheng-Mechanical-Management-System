package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.RgrwDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgrw;
import cn.jeeweb.modules.scgl.entity.ScglRgrw;

import java.util.List;

/**
 * Dscription: 日工 - 任务
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/27 14:01
 */
public interface IScglRgrwService extends ICommonService<ScglRgrw> {

    /**
     * Dscription: 展示所以日工任务
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 14:33
     */
    public PageJson<RgrwDTO> ajaxRcrwfpRwList(Queryable queryable, RgrwDTO rgrwDTO);


}
