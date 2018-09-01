package cn.jeeweb.modules.grgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.grgl.dto.YgzxxDTO;
import cn.jeeweb.modules.grgl.entity.Xzzwfp;

/**
 * @Description:    员工职位薪资分配
 * @Author:         杜凯之
 * @CreateDate:     2018/8/18 15:15
 * @Version:        1.0
 */
public interface IGrglXzzwfpService extends ICommonService<Xzzwfp> {
    /**
     * @Description:    查出所有员工的职位薪资分配
     * @Author:         杜凯之
     * @CreateDate:     2018/8/28 14:51
     * @Version:        1.0
     */
    public PageJson<YgzxxDTO> queryAjax(Queryable queryable, YgzxxDTO ygzxxDTO);
}
