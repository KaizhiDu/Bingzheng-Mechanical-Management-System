package cn.jeeweb.modules.ckgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.dto.CkglBhglDTO;
import cn.jeeweb.modules.ckgl.entity.CkglBhgl;

import java.util.List;

/**
 * Dscription: 仓库管理 - 补货管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/16 9:37
 */
public interface ICkglBhglService extends ICommonService<CkglBhgl>{

    /**
     * Dscription: 展示所有需要补货的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public PageJson<CkglBhglDTO> ajaxBhglList(Queryable queryable, CkglBhglDTO ckglBhglDTO);

    /**
     * Dscription: 导出
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 10:45
     */
    public List<CkglBhglDTO> bhglList();
}
