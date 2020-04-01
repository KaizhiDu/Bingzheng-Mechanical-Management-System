package cn.jeeweb.modules.htgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglHkxq;
import cn.jeeweb.modules.htgl.mapper.HtglHkxqMapper;
import cn.jeeweb.modules.htgl.service.IHtglHkxqService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("IHtglHkxqService")
public class HtglHkxqServiceImpl extends CommonServiceImpl<HtglHkxqMapper, HtglHkxq> implements IHtglHkxqService {

   @Autowired
   private HtglHkxqMapper htglHkxqMapper;

    @Override
    public PageJson<HtglHkxq> ajaxHkxqList(Queryable queryable, HtglHkxq htglHkxq) {
        Pageable pageable = queryable.getPageable();
        Page<HtglHkxq> page = new Page<HtglHkxq>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(htglHkxqMapper.ajaxHkxqList(page, htglHkxq));
        PageJson<HtglHkxq> pagejson = new PageJson<HtglHkxq>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
