package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.GybzglDTO;
import cn.jeeweb.modules.scgl.entity.ScglGybzgl;
import cn.jeeweb.modules.scgl.mapper.ScglGybzglMapper;
import cn.jeeweb.modules.scgl.service.IScglGybzglService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Dscription: 生产管理-工艺编制概览
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 9:46
 */
@Transactional
@Service("IScglGybzglService")
public class ScglGybzglServiceImpl extends CommonServiceImpl<ScglGybzglMapper, ScglGybzgl> implements IScglGybzglService {

    /**生产管理-工艺编制概览Mapper*/
    @Autowired
    private ScglGybzglMapper scglGybzglMapper;

    /**
     * Dscription: 展示所有工艺编制概览
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 10:09
     */
    @Override
    public PageJson<GybzglDTO> ajaxGybzglList(Queryable queryable, GybzglDTO gybzglDTO) {
        Pageable pageable = queryable.getPageable();
        Page<GybzglDTO> page = new Page<GybzglDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglGybzglMapper.ajaxGybzglList(page, gybzglDTO));
        PageJson<GybzglDTO> pagejson = new PageJson<GybzglDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 导出工艺
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/11/11 23:26
     */
    @Override
    public List<GybzglDTO> exprotGy(GybzglDTO gybzglDTO) {
        return scglGybzglMapper.exportGy(gybzglDTO);
    }
}
