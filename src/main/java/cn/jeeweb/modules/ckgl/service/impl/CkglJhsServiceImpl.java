package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglJhs;
import cn.jeeweb.modules.ckgl.mapper.CkglJhsMapper;
import cn.jeeweb.modules.ckgl.service.ICkglJhsService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 进货商
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/11/12 13:02
 */
@Transactional
@Service("ICkglJhsService")
public class CkglJhsServiceImpl extends CommonServiceImpl<CkglJhsMapper, CkglJhs> implements ICkglJhsService {

    /**仓库管理 - 进货商Mapper*/
    @Autowired
    private CkglJhsMapper ckglJhsMapper;

    @Override
    public PageJson<CkglJhs> ajaxJhsList(Queryable queryable, CkglJhs ckglJhs) {
        Pageable pageable = queryable.getPageable();
        Page<CkglJhs> page = new Page<CkglJhs>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglJhsMapper.ajaxJhsList(page, ckglJhs));
        PageJson<CkglJhs> pagejson = new PageJson<CkglJhs>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
