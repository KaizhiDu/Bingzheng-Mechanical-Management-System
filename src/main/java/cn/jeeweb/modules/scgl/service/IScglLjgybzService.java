package cn.jeeweb.modules.scgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.entity.ScglGymbxlsz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;

import java.util.List;

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

    /**
     * Dscription: 通过零件ID，得到所有下属工艺信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/10 12:20
     */
    public List<ScglLjgybz> getLjgybzByLjid(String ljid);

    /**
     * Dscription: 通过计划ID, 得到所有下属工艺信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 12:58
     */
    public List<ScglLjgybz> getLjgybzByJhid(String jhid);

    /**
     * Dscription: 通过计划ID和工艺大类编制ID, 得到所有下属工艺信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 13:35
     */
    public List<ScglLjgybz> getLjgybzByJhidGydlid(String jhid, String gydlid);

}
