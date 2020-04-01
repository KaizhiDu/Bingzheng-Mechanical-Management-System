package cn.jeeweb.modules.htglold.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htglold.entity.HtglHt;
import cn.jeeweb.modules.htglold.mapper.HtglHtMapper;
import cn.jeeweb.modules.htglold.service.IHtglHtService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 合同管理 - 合同
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/10 13:37
 */
@Transactional
@Service("IHtglHtService")
public class HtglHtServiceImpl extends CommonServiceImpl<HtglHtMapper, HtglHt> implements IHtglHtService {

    @Autowired
    private HtglHtMapper htglHtMapper;

    @Override
    public PageJson<HtglHt> ajaxHtList(Queryable queryable, HtglHt htglHt) {
        Pageable pageable = queryable.getPageable();
        Page<HtglHt> page = new Page<HtglHt>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(htglHtMapper.ajaxHtList(page, htglHt));
        PageJson<HtglHt> pagejson = new PageJson<HtglHt>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
