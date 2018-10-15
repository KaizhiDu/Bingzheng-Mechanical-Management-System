package cn.jeeweb.modules.ckgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.ckgl.entity.CkglDzyhp;
import cn.jeeweb.modules.ckgl.mapper.CkglDzyhpMapper;
import cn.jeeweb.modules.ckgl.service.ICkglDzyhpService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 仓库管理 - 低值易耗品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 12:17
 */
@Transactional
@Service("ICkglDzyhpService")
public class CkglDzyhpServiceImpl extends CommonServiceImpl<CkglDzyhpMapper, CkglDzyhp> implements ICkglDzyhpService {

    /**低值易耗品*/
    @Autowired
    private CkglDzyhpMapper ckglDzyhpMapper;

    /**
     * Dscription: 保存低值易耗品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 16:59
     */
    @Override
    public PageJson<CkglDzyhp> ajaxDzyhpList(Queryable queryable, CkglDzyhp ckglYcl) {
        Pageable pageable = queryable.getPageable();
        Page<CkglDzyhp> page = new Page<CkglDzyhp>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(ckglDzyhpMapper.ajaxDzyhpList(page, ckglYcl));
        PageJson<CkglDzyhp> pagejson = new PageJson<CkglDzyhp>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

}
