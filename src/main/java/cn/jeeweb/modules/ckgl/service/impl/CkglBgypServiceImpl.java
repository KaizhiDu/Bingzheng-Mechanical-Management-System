package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglBgyp;
import cn.jeeweb.modules.ckgl.mapper.CkglBgypMapper;
import cn.jeeweb.modules.ckgl.mapper.CkglYclMapper;
import cn.jeeweb.modules.ckgl.service.ICkglBgypService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 办公用品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 10:28
 */
@Transactional
@Service("ICkglBgypService")
public class CkglBgypServiceImpl extends CommonServiceImpl<CkglBgypMapper, CkglBgyp> implements ICkglBgypService {

    /**原材料*/
    @Autowired
    private CkglBgypMapper ckglBgypMapper;

    /**
     * Dscription: 展示所有办公用品的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    @Override
    public PageJson<CkglBgyp> ajaxBgypList(Queryable queryable, CkglBgyp ckglBgyp) {
        Pageable pageable = queryable.getPageable();
        Page<CkglBgyp> page = new Page<CkglBgyp>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglBgypMapper.ajaxBgypList(page, ckglBgyp));
        PageJson<CkglBgyp> pagejson = new PageJson<CkglBgyp>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
