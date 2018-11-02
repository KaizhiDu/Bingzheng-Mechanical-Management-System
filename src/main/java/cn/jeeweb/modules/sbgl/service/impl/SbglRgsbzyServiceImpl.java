package cn.jeeweb.modules.sbgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sbgl.entity.SbglRgsbzy;
import cn.jeeweb.modules.sbgl.mapper.SbglRgsbzyMapper;
import cn.jeeweb.modules.sbgl.service.ISbglRgsbzyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 设备管理 - 日工设备占用
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/11/2 13:20
 */
@Transactional
@Service("ISbglRgsbzyService")
public class SbglRgsbzyServiceImpl extends CommonServiceImpl<SbglRgsbzyMapper, SbglRgsbzy> implements ISbglRgsbzyService {
}
