package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglBcpWwc;
import cn.jeeweb.modules.ckgl.mapper.CkglBcpWwcMapper;
import cn.jeeweb.modules.ckgl.service.ICkglBcpWwcService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 已完成半成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 13:24
 */
@Transactional
@Service("ICkglBcpWwcService")
public class CkglBcpWwcServiceImpl extends CommonServiceImpl<CkglBcpWwcMapper, CkglBcpWwc> implements ICkglBcpWwcService {
}
