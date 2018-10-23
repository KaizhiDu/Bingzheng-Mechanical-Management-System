package cn.jeeweb.modules.scjhgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglFzjs;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglFzjsMapper;
import cn.jeeweb.modules.scjhgl.service.IScjhglFzjsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 生产计划管理 - 复制计数
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/23 14:20
 */
@Transactional
@Service("IScjhglFzjsService")
public class ScjhglFzjsServiceImpl extends CommonServiceImpl<ScjhglFzjsMapper, ScjhglFzjs> implements IScjhglFzjsService {
}
