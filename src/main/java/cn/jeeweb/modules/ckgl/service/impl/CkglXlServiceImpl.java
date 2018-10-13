package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.entity.CkglXl;
import cn.jeeweb.modules.ckgl.mapper.CkglXlMapper;
import cn.jeeweb.modules.ckgl.service.ICkglXlService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 小类
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/13 9:48
 */
@Transactional
@Service("ICkglXlService")
public class CkglXlServiceImpl extends CommonServiceImpl<CkglXlMapper, CkglXl> implements ICkglXlService {

    /**仓库管理 - 小类*/
    @Autowired
    private CkglXlMapper ckglXlMapper;

    /**
     * Dscription: 根据大类id查到下属工艺小类的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 12:55
     */
    @Override
    public PageJson<CkglXl> ajaxCkxlList(Queryable queryable, CkglXl ckglXl) {
        Pageable pageable = queryable.getPageable();
        Page<CkglXl> page = new Page<CkglXl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglXlMapper.ajaxCkxlList(page, ckglXl));
        PageJson<CkglXl> pagejson = new PageJson<CkglXl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
