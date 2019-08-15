package cn.jeeweb.modules.scjhgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglZdscth;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglZdscthMapper;
import cn.jeeweb.modules.scjhgl.service.IScjhglZdscthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("IScjhglZdscthService")
public class ScjhglZdscthServiceImpl extends CommonServiceImpl<ScjhglZdscthMapper, ScjhglZdscth> implements IScjhglZdscthService {
}
