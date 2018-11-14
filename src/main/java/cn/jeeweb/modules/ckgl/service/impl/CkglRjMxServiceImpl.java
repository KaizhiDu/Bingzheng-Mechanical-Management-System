package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglRjMx;
import cn.jeeweb.modules.ckgl.mapper.CkglRjMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglRjMxService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 刃具明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 9:22
 */
@Transactional
@Service("ICkglRjMxService")
public class CkglRjMxServiceImpl extends CommonServiceImpl<CkglRjMxMapper, CkglRjMx> implements ICkglRjMxService {

    /**原材料Mapper*/
    @Autowired
    private CkglRjMxMapper ckglRjMxMapper;


    @Override
    public PageJson<CkglRjMx> ajaxXqList(Queryable queryable, CkglRjMx ckglRjMx) {
        Pageable pageable = queryable.getPageable();
        Page<CkglRjMx> page = new Page<CkglRjMx>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglRjMxMapper.ajaxXqList(page, ckglRjMx));
        PageJson<CkglRjMx> pagejson = new PageJson<CkglRjMx>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
