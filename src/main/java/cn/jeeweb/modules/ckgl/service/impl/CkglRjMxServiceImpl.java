package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglRjMx;
import cn.jeeweb.modules.ckgl.mapper.CkglRjMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglRjMxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 刃具明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 9:22
 */
@Transactional
@Service("ICkglRjMxService")
public class CkglRjMxServiceImpl extends CommonServiceImpl<CkglRjMxMapper, CkglRjMx> implements ICkglRjMxService {
}
