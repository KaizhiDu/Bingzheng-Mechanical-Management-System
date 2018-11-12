package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglCpCkjl;
import cn.jeeweb.modules.ckgl.mapper.CkglCpCkjlMapper;
import cn.jeeweb.modules.ckgl.service.ICkglCpCkjlService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 成品出库记录
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/26 14:28
 */
@Transactional
@Service("ICkglCpCkjlService")
public class CkglCpCkjlServiceImpl extends CommonServiceImpl<CkglCpCkjlMapper, CkglCpCkjl> implements ICkglCpCkjlService {

    /**仓库管理 - 出库记录Mapper*/
    @Autowired
    private CkglCpCkjlMapper ckglCpCkjlMapper;

    @Override
    public PageJson<CkglCpCkjl> ajaxCpxqList(Queryable queryable, CkglCpCkjl ckglCpCkjl) {
        Pageable pageable = queryable.getPageable();
        Page<CkglCpCkjl> page = new Page<CkglCpCkjl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglCpCkjlMapper.ajaxCpxqList(page, ckglCpCkjl));
        PageJson<CkglCpCkjl> pagejson = new PageJson<CkglCpCkjl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
