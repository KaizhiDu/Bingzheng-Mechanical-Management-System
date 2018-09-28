package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.BgrwDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgrw;
import cn.jeeweb.modules.scgl.mapper.ScglBgrwMapper;
import cn.jeeweb.modules.scgl.service.IScglBgrwService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 包工 - 任务
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/27 14:01
 */
@Transactional
@Service("IScglBgrwService")
public class ScglBgrwServiceImpl extends CommonServiceImpl<ScglBgrwMapper, ScglBgrw> implements IScglBgrwService {

    /**包工 - 任务 Mapper*/
    @Autowired
    private ScglBgrwMapper scglBgrwMapper;

    /**
     * Dscription: 展示所有包工任务
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 14:33
     */
    @Override
    public PageJson<BgrwDTO> ajaxBgrwfpRwList(Queryable queryable, BgrwDTO bgrwDTO) {
        Pageable pageable = queryable.getPageable();
        Page<BgrwDTO> page = new Page<BgrwDTO>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglBgrwMapper.ajaxBgrwfpRwList(page, bgrwDTO));
        PageJson<BgrwDTO> pagejson = new PageJson<BgrwDTO>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
