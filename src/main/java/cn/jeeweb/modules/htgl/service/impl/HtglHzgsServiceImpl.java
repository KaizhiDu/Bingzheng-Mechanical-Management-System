package cn.jeeweb.modules.htgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.htgl.entity.HtglHzgs;
import cn.jeeweb.modules.htgl.mapper.HtglHzgsMapper;
import cn.jeeweb.modules.htgl.service.IHtglHzgsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("IHtglHzgsService")
public class HtglHzgsServiceImpl extends CommonServiceImpl<HtglHzgsMapper, HtglHzgs> implements IHtglHzgsService {
}
