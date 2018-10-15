package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglYclMx;
import cn.jeeweb.modules.ckgl.mapper.CkglYclMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglYclMxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 原材料明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 15:40
 */
@Transactional
@Service("ICkglYclMxService")
public class CkglYclMxServiceImpl extends CommonServiceImpl<CkglYclMxMapper, CkglYclMx> implements ICkglYclMxService {
}
