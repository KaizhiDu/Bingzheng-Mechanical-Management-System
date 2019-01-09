package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglWwcgydl;
import cn.jeeweb.modules.ckgl.mapper.CkglWwcgydlMapper;
import cn.jeeweb.modules.ckgl.service.ICkglWwcgydlSerivce;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 未完成工艺大类
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/8 14:59
 */
@Transactional
@Service("ICkglWwcgydlSerivce")
public class CkglWwcgydlSerivceImpl extends CommonServiceImpl<CkglWwcgydlMapper, CkglWwcgydl> implements ICkglWwcgydlSerivce {
}
