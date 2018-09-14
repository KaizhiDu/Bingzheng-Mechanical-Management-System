package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.mapper.ScglLjgybzMapper;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    生产管理-零件工艺编制
 * @Author:         杜凯之
 * @CreateDate:     2018/9/14 15:58
 * @Version:        1.0
 */
@Transactional
@Service("IScglLjgybzService")
public class ScglLjgybzServiceImpl extends CommonServiceImpl<ScglLjgybzMapper, ScglLjgybz> implements IScglLjgybzService {
}
