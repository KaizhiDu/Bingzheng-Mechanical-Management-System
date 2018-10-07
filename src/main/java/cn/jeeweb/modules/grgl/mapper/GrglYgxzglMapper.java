package cn.jeeweb.modules.grgl.mapper;

import cn.jeeweb.modules.grgl.entity.GrglYgxzgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 员工管理 - 员工薪资管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/3 12:25
 */
public interface GrglYgxzglMapper extends BaseMapper<GrglYgxzgl> {

    /**
     * Dscription: 得到所有员工薪资管理数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/6 17:56
     */
    public List<GrglYgxzgl> ajaxYgxzglList(Pagination page, @Param("entity") GrglYgxzgl entity);
}
