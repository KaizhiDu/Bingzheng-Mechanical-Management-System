package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglGj;
import cn.jeeweb.modules.ckgl.mapper.CkglGjMapper;
import cn.jeeweb.modules.ckgl.service.ICkglGjService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 工具
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 19:03
 */
@Transactional
@Service("ICkglGjService")
public class CkglGjServiceImpl extends CommonServiceImpl<CkglGjMapper, CkglGj> implements ICkglGjService {

    /**工具*/
    @Autowired
    private CkglGjMapper ckglGjMapper;

    /**
     * Dscription: 展示所有工具的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @Override
    public PageJson<CkglGj> ajaxGjList(Queryable queryable, CkglGj ckglGj) {
        Pageable pageable = queryable.getPageable();
        Page<CkglGj> page = new Page<CkglGj>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglGjMapper.ajaxGjList(page, ckglGj));
        PageJson<CkglGj> pagejson = new PageJson<CkglGj>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
