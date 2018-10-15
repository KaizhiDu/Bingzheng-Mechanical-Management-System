package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglBgypMx;
import cn.jeeweb.modules.ckgl.mapper.CkglBgypMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglBgypMxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 办公用品明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 10:37
 */
@Transactional
@Service("ICkglBgypMxService")
public class CkglBgypMxServiceImpl extends CommonServiceImpl<CkglBgypMxMapper, CkglBgypMx> implements ICkglBgypMxService {
}
