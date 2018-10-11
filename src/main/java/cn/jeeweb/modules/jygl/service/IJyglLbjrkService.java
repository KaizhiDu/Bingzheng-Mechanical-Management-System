package cn.jeeweb.modules.jygl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.jygl.entity.JyglLbjrk;

/**
 * Dscription: 检验管理 - 零部件入库
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 16:34
 */
public interface IJyglLbjrkService extends ICommonService<JyglLbjrk> {

    /**
     * @Description:    展示所有零部件信息（除了未完成入库的零部件）
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    public PageJson<JyglLbjrk> ajaxlbjglList(Queryable queryable, JyglLbjrk scjhglLjgl);
}
