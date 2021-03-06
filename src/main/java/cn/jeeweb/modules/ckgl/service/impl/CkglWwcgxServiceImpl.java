package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.ckgl.entity.CkglWwcgx;
import cn.jeeweb.modules.ckgl.mapper.CkglWwcgxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglWwcgxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Dscription: 仓库管理 - 未完成工序
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/11 13:35
 */
@Transactional
@Service("ICkglWwcgxService")
public class CkglWwcgxServiceImpl extends CommonServiceImpl<CkglWwcgxMapper, CkglWwcgx> implements ICkglWwcgxService {

    @Autowired
    private CkglWwcgxMapper ckglWwcgxMapper;

    @Override
    public List<CkglWwcgx> getData(String id) {
        return ckglWwcgxMapper.getData(id);
    }
}
