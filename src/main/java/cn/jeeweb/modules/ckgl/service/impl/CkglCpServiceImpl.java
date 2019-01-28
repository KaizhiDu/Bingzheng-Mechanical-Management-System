package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglCp;
import cn.jeeweb.modules.ckgl.mapper.CkglCpMapper;
import cn.jeeweb.modules.ckgl.service.ICkglCpService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/11 18:31
 */
@Transactional
@Service("ICkglCpService")
public class CkglCpServiceImpl extends CommonServiceImpl<CkglCpMapper, CkglCp> implements ICkglCpService {

    @Autowired
    private CkglCpMapper ckglCpMapper;

    @Override
    public PageJson<CkglCp> ajaxCpList(Queryable queryable, CkglCp ckglCp) {
        Pageable pageable = queryable.getPageable();
        Page<CkglCp> page = new Page<CkglCp>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglCpMapper.ajaxCpList(page, ckglCp));
        PageJson<CkglCp> pagejson = new PageJson<CkglCp>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
