package cn.jeeweb.modules.scjhgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglHtglMapper;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    生产计划管理-合同管理
 * @Author:         杜凯之
 * @CreateDate:     2018/9/12 14:22
 * @Version:        1.0
 */
@Transactional
@Service("IScjhglHtglSercice")
public class ScjhglHtglServiceImpl extends CommonServiceImpl<ScjhglHtglMapper, ScjhglHtgl> implements IScjhglHtglService {

    /**生产计划管理-计划管理Mapper*/
    @Autowired
    private ScjhglHtglMapper scjhglHtglMapper;

    /**
     * @Description:    展示所有计划信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/14 17:01
     * @Version:        1.0
     */
    @Override
    public PageJson<ScjhglHtgl> ajaxJhglList(Queryable queryable, ScjhglHtgl scjhglHtgl) {
        Pageable pageable = queryable.getPageable();
        Page<ScjhglHtgl> page = new Page<ScjhglHtgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scjhglHtglMapper.ajaxJhglList(page, scjhglHtgl));
        PageJson<ScjhglHtgl> pagejson = new PageJson<ScjhglHtgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
