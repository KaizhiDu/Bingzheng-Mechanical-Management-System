package cn.jeeweb.modules.grgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.modules.grgl.entity.GrglYgkqjlJcsj;
import cn.jeeweb.modules.grgl.mapper.GrglYgkqjlJcsjMapper;
import cn.jeeweb.modules.grgl.service.IGrglYgkqjlJcsjService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Dscription: 员工管理 - 员工考勤记录 - 基础数据
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/11/4 13:45
 */
@Transactional
@Service("IGrglYgkqjlJcsjService")
public class GrglYgkqjlJcsjServiceImpl extends CommonServiceImpl<GrglYgkqjlJcsjMapper, GrglYgkqjlJcsj> implements IGrglYgkqjlJcsjService {
}
