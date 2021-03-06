package cn.jeeweb.modules.htglold.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.htglold.entity.HtglGs;
import cn.jeeweb.modules.htglold.mapper.HtglGsMapper;
import cn.jeeweb.modules.htglold.service.IHtglGsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 合同管理 - 公司
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/9 13:40
 */
@Transactional
@Service("IHtglGsService")
public class HtglGsServiceImpl extends CommonServiceImpl<HtglGsMapper, HtglGs> implements IHtglGsService {
}
