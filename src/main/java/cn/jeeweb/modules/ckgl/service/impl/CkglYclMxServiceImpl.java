package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglYclMx;
import cn.jeeweb.modules.ckgl.mapper.CkglYclMxMapper;
import cn.jeeweb.modules.ckgl.service.ICkglYclMxService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 原材料明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 15:40
 */
@Transactional
@Service("ICkglYclMxService")
public class CkglYclMxServiceImpl extends CommonServiceImpl<CkglYclMxMapper, CkglYclMx> implements ICkglYclMxService {

    /**原材料Mapper*/
    @Autowired
    private CkglYclMxMapper ckglYclMxMapper;

    @Override
    public PageJson<CkglYclMx> ajaxXqList(Queryable queryable, CkglYclMx ckglYclMx) {
        Pageable pageable = queryable.getPageable();
        Page<CkglYclMx> page = new Page<CkglYclMx>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglYclMxMapper.ajaxXqList(page, ckglYclMx));
        PageJson<CkglYclMx> pagejson = new PageJson<CkglYclMx>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
