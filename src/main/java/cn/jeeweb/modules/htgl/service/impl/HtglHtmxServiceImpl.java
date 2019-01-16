package cn.jeeweb.modules.htgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglHtmx;
import cn.jeeweb.modules.htgl.mapper.HtglHtmxMapper;
import cn.jeeweb.modules.htgl.service.IHtglHtmxService;
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
@Service("IHtglHtmxService")
public class HtglHtmxServiceImpl extends CommonServiceImpl<HtglHtmxMapper, HtglHtmx> implements IHtglHtmxService {

    @Autowired
    private HtglHtmxMapper htglHtmxMapper;

    @Override
    public PageJson<HtglHtmx> ajaxHtmxList(Queryable queryable, HtglHtmx htglHtmx) {
        Pageable pageable = queryable.getPageable();
        Page<HtglHtmx> page = new Page<HtglHtmx>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(htglHtmxMapper.ajaxHtmxList(page, htglHtmx.getHtid(), htglHtmx.getLx()));
        PageJson<HtglHtmx> pagejson = new PageJson<HtglHtmx>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
