package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglDl;
import cn.jeeweb.modules.ckgl.mapper.CkglDlMapper;
import cn.jeeweb.modules.ckgl.service.ICkglDlService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 大类
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/13 9:48
 */
@Transactional
@Service("ICkglDlService")
public class CkglDlServiceImpl extends CommonServiceImpl<CkglDlMapper, CkglDl> implements ICkglDlService {
}
