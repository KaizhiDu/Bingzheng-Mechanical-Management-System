package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglCp;
import cn.jeeweb.modules.ckgl.mapper.CkglCpMapper;
import cn.jeeweb.modules.ckgl.service.ICkglCpService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/11 18:31
 */
@Transactional
@Service("ICkglCpService")
public class CkglCpServiceImpl extends CommonServiceImpl<CkglCpMapper, CkglCp> implements ICkglCpService {
}
