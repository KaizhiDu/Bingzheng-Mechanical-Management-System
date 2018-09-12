package cn.jeeweb.modules.scjhgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglLjglMapper;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    生产计划管理-零件管理
 * @Author:         杜凯之
 * @CreateDate:     2018/9/4 17:07
 * @Version:        1.0
 */
@Transactional
@Service("IScjhglLjglSercice")
public class ScjhglLjglServiceImpl extends CommonServiceImpl<ScjhglLjglMapper, ScjhglLjgl> implements IScjhglLjglService {

    /**生产计划管理-零件管理Mapper*/
    @Autowired
    private ScjhglLjglMapper scjhglLjglMapper;

    /**
     * @Description:    展示所有零件信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    @Override
    public PageJson<ScjhglLjgl> ajaxljglList(Queryable queryable, ScjhglLjgl scjhglLjgl) {
        Pageable pageable = queryable.getPageable();
        Page<ScjhglLjgl> page = new Page<ScjhglLjgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scjhglLjglMapper.ajaxljglList(page, scjhglLjgl));
        PageJson<ScjhglLjgl> pagejson = new PageJson<ScjhglLjgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
