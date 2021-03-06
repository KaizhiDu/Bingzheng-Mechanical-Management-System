package cn.jeeweb.modules.grgl.service;

import cn.jeeweb.core.common.service.ICommonService;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.grgl.dto.YgzxxDTO;
import cn.jeeweb.modules.grgl.entity.Grgl;

/**
* @Description:    员工管理
* @Author:         杜凯之
* @CreateDate:     2018/8/18 15:19
* @Version:        1.0
*/
public interface IGrglService extends ICommonService<Grgl> {

/**
* @Description:    展示员工信息
* @Author:         杜凯之
* @CreateDate:     2018/8/18 17:04
* @Version:        1.0
*/
public PageJson<Grgl> grglList(Queryable queryable, Grgl grgl);

/**
 * Dscription: 只得到所有的工人信息
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/4 14:57
 */
public PageJson<YgzxxDTO> ajaxListGrgl(Queryable queryable, YgzxxDTO ygzxxDTO);
}
