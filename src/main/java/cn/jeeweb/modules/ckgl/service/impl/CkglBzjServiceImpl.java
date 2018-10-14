package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglBzj;
import cn.jeeweb.modules.ckgl.mapper.CkglBzjMapper;
import cn.jeeweb.modules.ckgl.service.ICkglBzjService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 标准件
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/13 14:09
 */
@Transactional
@Service("ICkglBzjService")
public class CkglBzjServiceImpl extends CommonServiceImpl<CkglBzjMapper, CkglBzj> implements ICkglBzjService {

    /**标准件Mapper*/
    @Autowired
    private CkglBzjMapper ckglBzjMapper;

    /**
     * Dscription: 展示所有标准件的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @Override
    public PageJson<CkglBzj> ajaxBzjList(Queryable queryable, CkglBzj ckglBcp) {
        Pageable pageable = queryable.getPageable();
        Page<CkglBzj> page = new Page<CkglBzj>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglBzjMapper.ajaxBzjList(page, ckglBcp));
        PageJson<CkglBzj> pagejson = new PageJson<CkglBzj>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
