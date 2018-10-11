package cn.jeeweb.modules.jygl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.jygl.entity.JyglLbjrk;
import cn.jeeweb.modules.jygl.mapper.JyglLbjrkMapper;
import cn.jeeweb.modules.jygl.service.IJyglLbjrkService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 检验管理 - 零部件入库
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 16:34
 */
@Transactional
@Service("IYyglLbjrkService")
public class JyglLbjrkServiceImpl extends CommonServiceImpl<JyglLbjrkMapper, JyglLbjrk> implements IJyglLbjrkService {

    /**检验管理 - 零部件入库管理*/
     @Autowired
     private JyglLbjrkMapper jyglLbjrkMapper;

    /**
     * @Description:    展示所有零部件信息（除了未完成入库的零部件）
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    @Override
    public PageJson<JyglLbjrk> ajaxlbjglList(Queryable queryable, JyglLbjrk jyglLbjrk) {
        Pageable pageable = queryable.getPageable();
        Page<JyglLbjrk> page = new Page<JyglLbjrk>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(jyglLbjrkMapper.ajaxlbjglList(page, jyglLbjrk));
        PageJson<JyglLbjrk> pagejson = new PageJson<JyglLbjrk>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }
}
