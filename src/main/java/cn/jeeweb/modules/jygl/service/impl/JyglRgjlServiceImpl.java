package cn.jeeweb.modules.jygl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.jygl.entity.JyglRgjl;
import cn.jeeweb.modules.jygl.mapper.JyglRgjlMapper;
import cn.jeeweb.modules.jygl.service.IJyglRgjlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("IJyglRgjlService")
public class JyglRgjlServiceImpl extends CommonServiceImpl<JyglRgjlMapper, JyglRgjl> implements IJyglRgjlService {
}
