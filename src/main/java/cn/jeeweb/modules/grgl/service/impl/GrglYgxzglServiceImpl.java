package cn.jeeweb.modules.grgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import cn.jeeweb.modules.grgl.mapper.GrglMapper;
import cn.jeeweb.modules.grgl.mapper.GrglYgxzglMapper;
import cn.jeeweb.modules.grgl.service.IGrglYgxzglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 员工管理 - 员工薪资管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/3 12:25
 */
@Transactional
@Service("IGrglYgxzglService")
public class GrglYgxzglServiceImpl extends CommonServiceImpl<GrglYgxzglMapper, GrglYgxzgl> implements IGrglYgxzglService {

    /**员工管理Mapper*/
    @Autowired
    private GrglMapper grglMapper;

    /**员工管理 - 员工薪资管理Mapper*/
    @Autowired
    private GrglYgxzglMapper grglYgxzglMapper;
}
