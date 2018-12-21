package cn.jeeweb.modules.scjhgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.SsxDTO;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.mapper.ScglGydlbzMapper;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.mapper.ScjhglHtglMapper;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    /**生产计划管理-工艺大类编制Mapper*/
    @Autowired
    private ScglGydlbzMapper scglGydlbzMapper;
    /**
     * @Description:    展示所有计划信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/14 17:01
     * @Version:        1.0
     */
    @Override
    public PageJson<ScjhglHtgl> ajaxJhglList(Queryable queryable, ScjhglHtgl scjhglHtgl) {
        if (scjhglHtgl.getSfwc()==null){
            scjhglHtgl.setSfwc("0");
        }
        Pageable pageable = queryable.getPageable();
        Page<ScjhglHtgl> page = new Page<ScjhglHtgl>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scjhglHtglMapper.ajaxJhglList(page, scjhglHtgl));
        PageJson<ScjhglHtgl> pagejson = new PageJson<ScjhglHtgl>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 得到所有计划信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 12:47
     */
    @Override
    public List<SsxDTO> getJhList() {
        return scjhglHtglMapper.getJhList();
    }
}
