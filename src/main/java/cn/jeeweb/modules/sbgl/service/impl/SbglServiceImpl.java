package cn.jeeweb.modules.sbgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.sbgl.entity.Sbgl;
import cn.jeeweb.modules.sbgl.mapper.SbglMapper;
import cn.jeeweb.modules.sbgl.service.ISbglService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @Description:    设备管理
* @Author:         杜凯之
* @CreateDate:     2018/8/30 17:08
* @Version:        1.0
*/
@Transactional
@Service("ISbglService")
public class SbglServiceImpl  extends CommonServiceImpl<SbglMapper, Sbgl> implements ISbglService {
}
