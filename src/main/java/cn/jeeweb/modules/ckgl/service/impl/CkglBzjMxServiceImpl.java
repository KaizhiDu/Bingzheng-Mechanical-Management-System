package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglBzjMx;
import cn.jeeweb.modules.ckgl.mapper.CkglBzjMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglBzjMxSevice;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 标准件明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 13:30
 */
@Transactional
@Service("CkglBzjMxMapper")
public class CkglBzjMxServiceImpl extends CommonServiceImpl<CkglBzjMxMapper, CkglBzjMx> implements ICkglBzjMxSevice {

    /**仓库管理 - 标准间明细Mapper*/
    @Autowired
    private CkglBzjMxMapper ckglBzjMxMapper;

    @Override
    public PageJson<CkglBzjMx> ajaxXqList(Queryable queryable, CkglBzjMx ckglBzjMx) {
        Pageable pageable = queryable.getPageable();
        Page<CkglBzjMx> page = new Page<CkglBzjMx>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglBzjMxMapper.ajaxXqList(page, ckglBzjMx));
        PageJson<CkglBzjMx> pagejson = new PageJson<CkglBzjMx>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
