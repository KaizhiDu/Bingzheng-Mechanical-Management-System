package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglGjZjmx;
import cn.jeeweb.modules.ckgl.mapper.CkglGjZjmxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglGjZjmxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("ICkglGjZjmxService")
public class CkglGjZjmxServiceImpl extends CommonServiceImpl<CkglGjZjmxMapper, CkglGjZjmx> implements ICkglGjZjmxService {
}
