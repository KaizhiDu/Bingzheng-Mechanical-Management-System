package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglBcpMx;
import cn.jeeweb.modules.ckgl.mapper.CkglBcpMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglBcpMxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 半成品明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/31 13:22
 */
@Transactional
@Service("ICkglBcpMxService")
public class CkglBcpMxServiceImpl extends CommonServiceImpl<CkglBcpMxMapper, CkglBcpMx> implements ICkglBcpMxService {
}
