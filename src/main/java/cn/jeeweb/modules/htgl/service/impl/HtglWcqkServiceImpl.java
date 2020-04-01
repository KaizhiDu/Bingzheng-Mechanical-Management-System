package cn.jeeweb.modules.htgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglWcqk;
import cn.jeeweb.modules.htgl.mapper.HtglWcqkMapper;
import cn.jeeweb.modules.htgl.service.IHtglWcqkService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("IHtglWcqkService")
public class HtglWcqkServiceImpl extends CommonServiceImpl<HtglWcqkMapper, HtglWcqk> implements IHtglWcqkService {

    @Autowired
    private HtglWcqkMapper htglWcqkMapper;

    @Override
    public PageJson<HtglWcqk> ajaxWcqkList(Queryable queryable, HtglWcqk htglWcqk) {
        Pageable pageable = queryable.getPageable();
        Page<HtglWcqk> page = new Page<HtglWcqk>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(htglWcqkMapper.ajaxWcqkList(page, htglWcqk));
        PageJson<HtglWcqk> pagejson = new PageJson<HtglWcqk>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
