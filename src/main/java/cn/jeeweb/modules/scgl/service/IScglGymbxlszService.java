package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.entity.ScglGymbxlsz;
/**
* @Description:    工艺小类设置
* @Author:         杜凯之
* @CreateDate:     2018/9/11 9:48
* @Version:        1.0
*/
public interface IScglGymbxlszService extends ICommonService<ScglGymbxlsz> {
    /**
     * @Description:    工艺模板小类信息展示
     * @Author:         杜凯之
     * @CreateDate:     2018/9/11 10:01
     * @Version:        1.0
     */
    public PageJson<ScglGymbxlsz> gymbxlszList(Queryable queryable, ScglGymbxlsz scglGymbxlsz, String dlid);
}
