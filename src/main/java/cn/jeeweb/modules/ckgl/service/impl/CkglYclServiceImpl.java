package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglYcl;
import cn.jeeweb.modules.ckgl.mapper.CkglYclMapper;
import cn.jeeweb.modules.ckgl.service.ICkglYclService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 原材料
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 15:09
 */
@Transactional
@Service("ICkglYclService")
public class CkglYclServiceImpl extends CommonServiceImpl<CkglYclMapper, CkglYcl> implements ICkglYclService {

    /**原材料*/
    @Autowired
    private CkglYclMapper ckglYclMapper;

    /**
     * Dscription: 展示所有原材料的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @Override
    public PageJson<CkglYcl> ajaxYclList(Queryable queryable, CkglYcl ckglYcl) {
        Pageable pageable = queryable.getPageable();
        Page<CkglYcl> page = new Page<CkglYcl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglYclMapper.ajaxYclList(page, ckglYcl));
        PageJson<CkglYcl> pagejson = new PageJson<CkglYcl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
