package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.entity.ScglGymbsz;
import cn.jeeweb.modules.scgl.mapper.ScglGymbszMapper;
import cn.jeeweb.modules.scgl.service.IScglGymbszService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    生产管理-工艺模板设置
 * @Author:         杜凯之
 * @CreateDate:     2018/9/5 14:21
 * @Version:        1.0
 */
@Transactional
@Service("IScglGymbszService")
public class ScglGymbszServiceImpl extends CommonServiceImpl<ScglGymbszMapper, ScglGymbsz> implements IScglGymbszService {

    /**工艺模板大类Mapper*/
    @Autowired
    private ScglGymbszMapper scglGymbszMapper;

    @Override
    public PageJson<ScglGymbsz> gymbszList(Queryable queryable, ScglGymbsz scglGymbsz) {
        Pageable pageable = queryable.getPageable();
        Page<ScglGymbsz> page = new Page<ScglGymbsz>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglGymbszMapper.gymbszList(page, scglGymbsz));
        PageJson<ScglGymbsz> pagejson = new PageJson<ScglGymbsz>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

}
