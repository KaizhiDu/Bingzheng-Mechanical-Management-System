package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglRj;
import cn.jeeweb.modules.ckgl.entity.CkglYcl;
import cn.jeeweb.modules.ckgl.mapper.CkglRjMapper;
import cn.jeeweb.modules.ckgl.service.ICkglRjService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 刃具
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 9:13
 */
@Transactional
@Service("ICkglRjService")
public class CkglRjServiceImpl extends CommonServiceImpl<CkglRjMapper, CkglRj> implements ICkglRjService {

    /**刃具*/
    @Autowired
    private CkglRjMapper ckglRjMapper;

    /**
     * Dscription: 展示所有刃具的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @Override
    public PageJson<CkglRj> ajaxRjList(Queryable queryable, CkglRj ckglRj) {
        Pageable pageable = queryable.getPageable();
        Page<CkglRj> page = new Page<CkglRj>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglRjMapper.ajaxRjList(page, ckglRj));
        PageJson<CkglRj> pagejson = new PageJson<CkglRj>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
