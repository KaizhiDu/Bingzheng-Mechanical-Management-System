package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.Ckgl;
import cn.jeeweb.modules.ckgl.mapper.CkglMapper;
import cn.jeeweb.modules.ckgl.service.ICkglService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 13:24
 */
@Transactional
@Service("IKcglService")
public class CkglServiceImpl extends CommonServiceImpl<CkglMapper, Ckgl> implements ICkglService {
}
