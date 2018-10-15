package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglGjMx;
import cn.jeeweb.modules.ckgl.mapper.CkglGjMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglGjMxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 工具明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 19:09
 */
@Transactional
@Service("ICkglGjMxService")
public class CkglGjMxServiceImpl extends CommonServiceImpl<CkglGjMxMapper, CkglGjMx> implements ICkglGjMxService {
}
