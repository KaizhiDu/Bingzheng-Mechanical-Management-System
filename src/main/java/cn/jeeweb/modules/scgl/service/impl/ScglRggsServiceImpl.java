package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.scgl.entity.ScglRggs;
import cn.jeeweb.modules.scgl.mapper.ScglRggsMapper;
import cn.jeeweb.modules.scgl.service.IScglRggsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 日工 - 工时
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/26 9:40
 */
@Transactional
@Service("IScglRggsService")
public class ScglRggsServiceImpl extends CommonServiceImpl<ScglRggsMapper, ScglRggs> implements IScglRggsService {
}
