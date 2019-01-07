package cn.jeeweb.modules.jcsz.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.jcsz.entity.JcszMxmb;
import cn.jeeweb.modules.jcsz.mapper.JcszMxmbMapper;
import cn.jeeweb.modules.jcsz.service.IJcszMxmbService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 基础数据 - 明细模板
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/3 15:38
 */
@Transactional
@Service("IJcszMxmbService")
public class JcszMxmbServiceImpl extends CommonServiceImpl<JcszMxmbMapper, JcszMxmb> implements IJcszMxmbService {

    /**基础数据 - 明细模板Mapper*/
    @Autowired
    private JcszMxmbMapper jcszMxmbMapper;

    /**
     * Dscription: 展示所有明细模板信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/4 15:31
     */
    @Override
    public PageJson<JcszMxmb> ajaxMxmbList(Queryable queryable, JcszMxmb jcszMxmb) {
        Pageable pageable = queryable.getPageable();
        Page<JcszMxmb> page = new Page<JcszMxmb>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(jcszMxmbMapper.ajaxMxmbList(page, jcszMxmb));
        PageJson<JcszMxmb> pagejson = new PageJson<JcszMxmb>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
