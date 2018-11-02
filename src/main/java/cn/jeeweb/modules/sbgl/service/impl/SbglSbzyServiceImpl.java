package cn.jeeweb.modules.sbgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sbgl.entity.SbglSbzy;
import cn.jeeweb.modules.sbgl.mapper.SbglSbzyMapper;
import cn.jeeweb.modules.sbgl.service.ISbglSbzyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 设备管理 - 设备占用
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/11/2 16:56
 */
@Transactional
@Service("ISbglSbzyService")
public class SbglSbzyServiceImpl extends CommonServiceImpl<SbglSbzyMapper, SbglSbzy> implements ISbglSbzyService {
}
