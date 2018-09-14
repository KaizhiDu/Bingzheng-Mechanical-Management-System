package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.mapper.ScglGydlbzMapper;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    生产管理-工艺大类编制
 * @Author:         杜凯之
 * @CreateDate:     2018/9/14 18:03
 * @Version:        1.0
 */
@Transactional
@Service("IScglGydlbzService")
public class ScglGydlbzServiceImpl extends CommonServiceImpl<ScglGydlbzMapper, ScglGydlbz> implements IScglGydlbzService {
}
