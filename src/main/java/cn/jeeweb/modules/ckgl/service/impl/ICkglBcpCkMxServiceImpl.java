package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglBcpCkMx;
import cn.jeeweb.modules.ckgl.mapper.CkglBcpCkMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglBcpCkMxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("ICkglBcpCkMxService")
public class ICkglBcpCkMxServiceImpl extends CommonServiceImpl<CkglBcpCkMxMapper, CkglBcpCkMx> implements ICkglBcpCkMxService {
}
