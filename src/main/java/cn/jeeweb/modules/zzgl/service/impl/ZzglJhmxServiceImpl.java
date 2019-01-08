package cn.jeeweb.modules.zzgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.zzgl.entity.ZzglJhmx;
import cn.jeeweb.modules.zzgl.mapper.ZzglJhmxMapper;
import cn.jeeweb.modules.zzgl.service.IZzglJhmxService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 资金管理 - 借还明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/7 16:09
 */
@Transactional
@Service("IZzglJhmxService")
public class ZzglJhmxServiceImpl extends CommonServiceImpl<ZzglJhmxMapper, ZzglJhmx> implements IZzglJhmxService {
}
