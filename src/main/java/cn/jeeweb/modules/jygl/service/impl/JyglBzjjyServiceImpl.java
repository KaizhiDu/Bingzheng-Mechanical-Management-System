package cn.jeeweb.modules.jygl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.jygl.entity.JyglBzjjy;
import cn.jeeweb.modules.jygl.mapper.JyglBzjjyMapper;
import cn.jeeweb.modules.jygl.service.IJyglBzjjyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Dscription: 标准件检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/12/24 13:08
 */
@Transactional
@Service("IJyglBzjjyService")
public class JyglBzjjyServiceImpl extends CommonServiceImpl<JyglBzjjyMapper, JyglBzjjy> implements IJyglBzjjyService {

   /**标准件检验*/
   @Autowired
   private JyglBzjjyMapper jyglBzjjyMapper;

    /**
     * Dscription: 导出标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/24 11:19
     */
    @Override
    public List<JyglBzjjy> exportBzj(JyglBzjjy jyglBzjjy) {
        return jyglBzjjyMapper.exportBzj(jyglBzjjy);
    }
}
