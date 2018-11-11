package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.GybzglDTO;
import cn.jeeweb.modules.scgl.entity.ScglGybzgl;

import java.util.List;

/**
 * Dscription: 生产管理-工艺编制概览
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 9:46
 */
public interface IScglGybzglService extends ICommonService<ScglGybzgl> {

    /**
     * Dscription: 展示所有工艺编制概览
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 10:09
     */
    public PageJson<GybzglDTO> ajaxGybzglList(Queryable queryable, GybzglDTO gybzglDTO);

    /**
     * Dscription: 导出工艺
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/11 23:26
     */
    public List<GybzglDTO> exprotGy(GybzglDTO gybzglDTO);
}
