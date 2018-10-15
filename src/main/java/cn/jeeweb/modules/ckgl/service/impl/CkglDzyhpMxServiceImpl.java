package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglDzyhpMx;
import cn.jeeweb.modules.ckgl.mapper.CkglDzyhpMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglDzyhpMxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 低值易耗品明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 12:28
 */
@Transactional
@Service("ICkglDzyhpMxService")
public class CkglDzyhpMxServiceImpl extends CommonServiceImpl<CkglDzyhpMxMapper, CkglDzyhpMx> implements ICkglDzyhpMxService {
}
