package cn.jeeweb.modules.scjhgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.SsxDTO;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;

import java.util.List;

/**
 * @Description:    生产计划管理-零部件管理
 * @Author:         杜凯之
 * @CreateDate:     2018/9/4 17:07
 * @Version:        1.0
 */
public interface IScjhglLjglService extends ICommonService<ScjhglLjgl> {
    /**
     * @Description:    展示所有零部件信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    public PageJson<ScjhglLjgl> ajaxljglList(String pxfs, Queryable queryable, ScjhglLjgl scjhglLjgl);

    /**
     * Dscription: 导出零件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/11 22:39
     */
    public List<ScjhglLjgl> exportLj(String pxfs, ScjhglLjgl scjhglLjgl);

    /**
     * Dscription: 根据计划ID获取所有的零部件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 12:55
     */
    public List<SsxDTO> cxLj(String jhid);

    public PageJson<ScjhglLjgl> ajaxlbjglList(Queryable queryable, ScjhglLjgl scjhglLjgl);

    /**
     * Dscription: 根据计划ID获取所有的零部件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/23 15:21
     */
    public List<ScjhglLjgl> getLjByjhid(String jhid);
}
