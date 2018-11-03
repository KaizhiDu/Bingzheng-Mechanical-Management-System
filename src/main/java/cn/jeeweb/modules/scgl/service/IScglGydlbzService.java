package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.GydlbzDTO;
import cn.jeeweb.modules.scgl.dto.SsxDTO;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;

import java.util.List;

/**
 * @Description:    生产管理-工艺大类编制
 * @Author:         杜凯之
 * @CreateDate:     2018/9/14 18:03
 * @Version:        1.0
 */
public interface IScglGydlbzService extends ICommonService<ScglGydlbz> {
    /**
     * @Description:    根据计划id得到所有大类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 9:30
     * @Version:        1.0
     */
    public PageJson<GydlbzDTO> ajaxGydlbzList(Queryable queryable, GydlbzDTO gydlbzDTO);

    /**
     * Dscription: 根据根据零部件id得到所有大类信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 13:13
     */
    public List<SsxDTO> cxGydl(String ljid);

    /**
     * @Description:    根据计划id得到所有大类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 9:30
     * @Version:        1.0
     */
    public List<ScglGydlbz> getGydlbzByjhid(String jhid);
}
