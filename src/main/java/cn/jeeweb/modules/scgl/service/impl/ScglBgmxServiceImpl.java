package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.scgl.entity.ScglBgmx;
import cn.jeeweb.modules.scgl.mapper.ScglBgmxMapper;
import cn.jeeweb.modules.scgl.service.IScglBgmxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 包工 - 明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/28 15:59
 */
@Transactional
@Service("IScglBgmxService")
public class ScglBgmxServiceImpl extends CommonServiceImpl<ScglBgmxMapper, ScglBgmx> implements IScglBgmxService {
}
