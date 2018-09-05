package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.scgl.entity.ScglGymbsz;
import cn.jeeweb.modules.scgl.mapper.ScglGymbszMapper;
import cn.jeeweb.modules.scgl.service.IScglGymbszService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    生产管理-工艺模板设置
 * @Author:         杜凯之
 * @CreateDate:     2018/9/5 14:21
 * @Version:        1.0
 */
@Transactional
@Service("IScglGymbszService")
public class ScglGymbszServiceImpl extends CommonServiceImpl<ScglGymbszMapper, ScglGymbsz> implements IScglGymbszService {
}
