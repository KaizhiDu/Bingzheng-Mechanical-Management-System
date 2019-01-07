package cn.jeeweb.modules.jcsz.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.jcsz.entity.JcszZzse;
import cn.jeeweb.modules.jcsz.mapper.JcszZzseMapper;
import cn.jeeweb.modules.jcsz.service.IJcszZzseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 基础数据 - 资金数额
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/3 15:38
 */
@Transactional
@Service("IJcszZzseService")
public class JcszZzseServiceImpl extends CommonServiceImpl<JcszZzseMapper, JcszZzse> implements IJcszZzseService {
}
