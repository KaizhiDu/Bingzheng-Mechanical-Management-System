package cn.jeeweb.modules.scjhgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglHtglMapper;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    生产计划管理-合同管理
 * @Author:         杜凯之
 * @CreateDate:     2018/9/12 14:22
 * @Version:        1.0
 */
@Transactional
@Service("IScjhglHtglSercice")
public class ScjhglHtglServiceImpl extends CommonServiceImpl<ScjhglHtglMapper, ScjhglHtgl> implements IScjhglHtglService {
}
