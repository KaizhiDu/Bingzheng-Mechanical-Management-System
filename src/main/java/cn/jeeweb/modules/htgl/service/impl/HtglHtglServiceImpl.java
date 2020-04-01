package cn.jeeweb.modules.htgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.htgl.entity.HtglHtgl;
import cn.jeeweb.modules.htgl.mapper.HtglHtglMapper;
import cn.jeeweb.modules.htgl.service.IHtglHtglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Dscription: 合同管理 - 合同管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/3/21 16:40
 */

@Transactional
@Service("IHtglHtglService")
public class HtglHtglServiceImpl extends CommonServiceImpl<HtglHtglMapper, HtglHtgl> implements IHtglHtglService {

    @Autowired
    private HtglHtglMapper htglHtglMapper;

    @Override
    public PageJson<HtglHtgl> ajaxHtglList(Queryable queryable, HtglHtgl htglHtgl) {
        Pageable pageable = queryable.getPageable();
        Page<HtglHtgl> page = new Page<HtglHtgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(htglHtglMapper.ajaxHtglList(page, htglHtgl));
        PageJson<HtglHtgl> pagejson = new PageJson<HtglHtgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
