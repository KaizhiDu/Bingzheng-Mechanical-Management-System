package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglBzjMx;
import cn.jeeweb.modules.ckgl.mapper.CkglBzjMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglBzjMxSevice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 标准件明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 13:30
 */
@Transactional
@Service("CkglBzjMxMapper")
public class CkglBzjMxServiceImpl extends CommonServiceImpl<CkglBzjMxMapper, CkglBzjMx> implements ICkglBzjMxSevice {
}
