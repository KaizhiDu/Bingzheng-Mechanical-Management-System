package cn.jeeweb.modules.scjhgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBzjgl;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglBzjglMapper;
import cn.jeeweb.modules.scjhgl.service.IScjhglBzjglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Dscription: 生产计划管理 - 标准件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/12/21 16:27
 */
@Transactional
@Service("IScjhglBzjglService")
public class ScjhglBzjglServiceImpl extends CommonServiceImpl<ScjhglBzjglMapper, ScjhglBzjgl> implements IScjhglBzjglService {

    /**标准件管理Mapper*/
    @Autowired
    private ScjhglBzjglMapper scjhglBzjglMapper;

    /**
     * @Description:    展示所有标准件信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    @Override
    public PageJson<ScjhglBzjgl> ajaxBzjList(Queryable queryable, ScjhglBzjgl scjhglBzjgl) {
        Pageable pageable = queryable.getPageable();
        Page<ScjhglBzjgl> page = new Page<ScjhglBzjgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scjhglBzjglMapper.ajaxBzjList(page, scjhglBzjgl));
        PageJson<ScjhglBzjgl> pagejson = new PageJson<ScjhglBzjgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 导出标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/24 11:19
     */
    @Override
    public List<ScjhglBzjgl> exportBzj(ScjhglBzjgl scjhglBzjgl) {
        return scjhglBzjglMapper.exportBzj(scjhglBzjgl);
    }
}
