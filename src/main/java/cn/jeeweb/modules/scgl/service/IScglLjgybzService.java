package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.entity.ScglGymbxlsz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;

/**
 * @Description:    生产管理-零件工艺编制
 * @Author:         杜凯之
 * @CreateDate:     2018/9/14 15:58
 * @Version:        1.0
 */
public interface IScglLjgybzService extends ICommonService<ScglLjgybz> {

    /**
     * @Description:    根据工艺大类编制ID获取所有小类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/18 16:24
     * @Version:        1.0
     */
    public PageJson<ScglLjgybz> ajaxGyxlbzList(Queryable queryable, ScglLjgybz scglLjgybz, String gydlbzid);
}
