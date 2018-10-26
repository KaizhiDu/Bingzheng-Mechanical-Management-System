package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglCpCkjl;
import cn.jeeweb.modules.ckgl.mapper.CkglCpCkjlMapper;
import cn.jeeweb.modules.ckgl.service.ICkglCpCkjlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 成品出库记录
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/26 14:28
 */
@Transactional
@Service("ICkglCpCkjlService")
public class CkglCpCkjlServiceImpl extends CommonServiceImpl<CkglCpCkjlMapper, CkglCpCkjl> implements ICkglCpCkjlService {
}
