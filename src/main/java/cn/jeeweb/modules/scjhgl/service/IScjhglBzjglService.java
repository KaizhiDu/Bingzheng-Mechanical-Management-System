package cn.jeeweb.modules.scjhgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBzjgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;

import java.util.List;

/**
 * Dscription: 生产计划管理 - 标准件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/12/21 16:27
 */
public interface IScjhglBzjglService extends ICommonService<ScjhglBzjgl>{

    /**
     * @Description:    展示所有标准件信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    public PageJson<ScjhglBzjgl> ajaxBzjList(Queryable queryable, ScjhglBzjgl scjhglBzjgl);

    /**
     * Dscription: 导出标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/24 11:19
     */
    public List<ScjhglBzjgl> exportBzj(ScjhglBzjgl scjhglBzjgl);


}
