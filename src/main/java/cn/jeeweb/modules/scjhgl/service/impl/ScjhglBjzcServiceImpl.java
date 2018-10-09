package cn.jeeweb.modules.scjhgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBjzc;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglBjzcMapper;
import cn.jeeweb.modules.scjhgl.service.IScjhglBjzcService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 生产计划管理 - 部件组成
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/9 13:58
 */
@Transactional
@Service("IScjhglBjzcService")
public class ScjhglBjzcServiceImpl extends CommonServiceImpl<ScjhglBjzcMapper, ScjhglBjzc> implements IScjhglBjzcService {
}
