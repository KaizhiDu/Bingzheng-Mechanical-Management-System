package cn.jeeweb.modules.sbgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.sbgl.entity.Sbgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbzy;

/**
 * @Description:    设备管理
 * @Author:         杜凯之
 * @CreateDate:     2018/8/30 17:00
 * @Version:        1.0
 */
public interface ISbglService extends ICommonService<Sbgl>{
    /**
     * @Description:    展示所有设备（日工）
     * @Author:         杜凯之
     * @CreateDate:     2018/8/30 17:58
     * @Version:        1.0
     */
    public PageJson<SbglSbzy> ajaxListSbgl(Queryable queryable, SbglSbzy sbglSbzy, String addSb);

    /**
     * @Description:    展示所有设备（包工）
     * @Author:         杜凯之
     * @CreateDate:     2018/8/30 17:58
     * @Version:        1.0
     */
    public PageJson<Sbgl> ajaxListSbgl2(Queryable queryable, Sbgl sbgl, String addSb);
}
