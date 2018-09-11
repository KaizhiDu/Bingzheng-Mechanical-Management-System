package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.entity.ScglGymbxlsz;
import cn.jeeweb.modules.scgl.mapper.ScglGymbszMapper;
import cn.jeeweb.modules.scgl.mapper.ScglGymbxlszMapper;
import cn.jeeweb.modules.scgl.service.IScglGymbxlszService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:    工艺小类设置
 * @Author:         杜凯之
 * @CreateDate:     2018/9/11 9:48
 * @Version:        1.0
 */
@Transactional
@Service("IScglGymbxlszService")
public class ScglGymbxlszServiceImpl extends CommonServiceImpl<ScglGymbxlszMapper, ScglGymbxlsz> implements IScglGymbxlszService {

    /**工艺模板小类Mapper*/
    @Autowired
    private ScglGymbxlszMapper scglGymbxlszMapper;

    /**
     * @Description:    工艺模板小类信息展示
     * @Author:         杜凯之
     * @CreateDate:     2018/9/11 10:01
     * @Version:        1.0
     */
    @Override
    public PageJson<ScglGymbxlsz> gymbxlszList(Queryable queryable, ScglGymbxlsz scglGymbxlsz) {
        Pageable pageable = queryable.getPageable();
        Page<ScglGymbxlsz> page = new Page<ScglGymbxlsz>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglGymbxlszMapper.gymbxlszList(page, scglGymbxlsz));
        PageJson<ScglGymbxlsz> pagejson = new PageJson<ScglGymbxlsz>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
