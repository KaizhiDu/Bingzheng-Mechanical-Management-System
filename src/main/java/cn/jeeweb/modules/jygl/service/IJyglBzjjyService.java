package cn.jeeweb.modules.jygl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.modules.jygl.entity.JyglBzjjy;

import java.util.List;

/**
 * Dscription: 标准件检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/12/24 13:08
 */
public interface IJyglBzjjyService extends ICommonService<JyglBzjjy>{
    /**
     * Dscription: 导出标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/24 11:19
     */
    public List<JyglBzjjy> exportBzj(JyglBzjjy jyglBzjjy);

}
