package cn.jeeweb.modules.scjhgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBjgl;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglBjglMapper;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglLjglMapper;
import cn.jeeweb.modules.scjhgl.service.IScjhglBjglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 生产计划管理 - 部件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/8 13:06
 */
@Transactional
@Service("IScjhglBjglService")
public class ScjhglBjglServiceImpl extends CommonServiceImpl<ScjhglBjglMapper, ScjhglBjgl> implements IScjhglBjglService {

    /**生产计划管理-部件管理Mapper*/
    @Autowired
    private ScjhglBjglMapper scjhglBjglMapper;

    /**
     * Dscription: 展示所有部件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/8 17:06
     */
    @Override
    public PageJson<ScjhglBjgl> ajaxBjglList(Queryable queryable, ScjhglBjgl scjhglBjgl) {
        Pageable pageable = queryable.getPageable();
        Page<ScjhglBjgl> page = new Page<ScjhglBjgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scjhglBjglMapper.ajaxBjglList(page, scjhglBjgl));
        PageJson<ScjhglBjgl> pagejson = new PageJson<ScjhglBjgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
