package cn.jeeweb.modules.sbgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sbgl.entity.SbglBgsbzy;
import cn.jeeweb.modules.sbgl.mapper.SbglBgsbzyMapper;
import cn.jeeweb.modules.sbgl.service.ISbglBgsbzyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 设备管理 - 包工设备占用
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/11/2 14:04
 */
@Transactional
@Service("ISbglBgsbzyService")
public class SbglBgsbzyServiceImpl extends CommonServiceImpl<SbglBgsbzyMapper, SbglBgsbzy> implements ISbglBgsbzyService {
}
