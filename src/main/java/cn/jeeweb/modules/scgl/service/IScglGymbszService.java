package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.entity.ScglGymbsz;

/**
 * @Description:    生产管理-工艺模板设置
 * @Author:         杜凯之
 * @CreateDate:     2018/9/5 14:21
 * @Version:        1.0
 */
public interface IScglGymbszService extends ICommonService<ScglGymbsz> {
    /**
     * @Description:    展示所有工艺大类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/8/18 16:58
     * @Version:        1.0
     */
    public PageJson<ScglGymbsz> gymbszList(Queryable queryable, ScglGymbsz scglGymbsz);

}
