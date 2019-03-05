package cn.jeeweb.modules.zjls.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.zjls.entity.ZjlsZj;
import cn.jeeweb.modules.zjls.mapper.ZjlsZjMapper;
import cn.jeeweb.modules.zjls.service.ZjlsZjService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 资金流水 - 资金
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/2/17 18:36
 */
@Transactional
@Service("ZjlsZjService")
public class ZjlsZjServiceImpl extends CommonServiceImpl<ZjlsZjMapper, ZjlsZj> implements ZjlsZjService {
}
