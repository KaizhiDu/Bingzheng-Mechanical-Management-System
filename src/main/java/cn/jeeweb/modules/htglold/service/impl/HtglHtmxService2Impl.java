package cn.jeeweb.modules.htglold.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htglold.entity.HtglHtmx2;
import cn.jeeweb.modules.htglold.mapper.HtglHtmxMapper2;
import cn.jeeweb.modules.htglold.service.IHtglHtmxService2;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 合同管理 - 合同明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/11 13:40
 */
@Transactional
@Service("IHtglHtmxService2")
public class HtglHtmxService2Impl extends CommonServiceImpl<HtglHtmxMapper2, HtglHtmx2> implements IHtglHtmxService2 {

    @Autowired
    private HtglHtmxMapper2 htglHtmxMapper2;

    @Override
    public PageJson<HtglHtmx2> ajaxHtmxList(Queryable queryable, HtglHtmx2 htglHtmx2) {
        Pageable pageable = queryable.getPageable();
        Page<HtglHtmx2> page = new Page<HtglHtmx2>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(htglHtmxMapper2.ajaxHtmxList(page, htglHtmx2.getHtid(), htglHtmx2.getLx()));
        PageJson<HtglHtmx2> pagejson = new PageJson<HtglHtmx2>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
