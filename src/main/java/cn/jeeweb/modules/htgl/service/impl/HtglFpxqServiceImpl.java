package cn.jeeweb.modules.htgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglFpxq;
import cn.jeeweb.modules.htgl.mapper.HtglFpxqMapper;
import cn.jeeweb.modules.htgl.service.IHtglFpxqService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("IHtglFpxqService")
public class HtglFpxqServiceImpl extends CommonServiceImpl<HtglFpxqMapper, HtglFpxq> implements IHtglFpxqService{

    @Autowired
    private HtglFpxqMapper htglFpxqMapper;

    @Override
    public PageJson<HtglFpxq> ajaxFpxqList(Queryable queryable, HtglFpxq htglFpxq) {
        Pageable pageable = queryable.getPageable();
        Page<HtglFpxq> page = new Page<HtglFpxq>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(htglFpxqMapper.ajaxFpxqList(page, htglFpxq));
        PageJson<HtglFpxq> pagejson = new PageJson<HtglFpxq>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
