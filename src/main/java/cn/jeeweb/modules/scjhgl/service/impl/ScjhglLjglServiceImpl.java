package cn.jeeweb.modules.scjhgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglLjglMapper;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    生产计划管理-零件管理
 * @Author:         杜凯之
 * @CreateDate:     2018/9/4 17:07
 * @Version:        1.0
 */
@Transactional
@Service("IScjhglLjglSercice")
public class ScjhglLjglServiceImpl extends CommonServiceImpl<ScjhglLjglMapper, ScjhglLjgl> implements IScjhglLjglService {
}
