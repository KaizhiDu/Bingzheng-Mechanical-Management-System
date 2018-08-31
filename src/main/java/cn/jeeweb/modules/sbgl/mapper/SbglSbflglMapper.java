package cn.jeeweb.modules.sbgl.mapper;

import cn.jeeweb.modules.grgl.entity.Grgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    设备管理-设备分类管理
 * @Author:         杜凯之
 * @CreateDate:     2018/8/31 16:47
 * @Version:        1.0
 */
public interface SbglSbflglMapper extends BaseMapper<SbglSbflgl> {

    /**
     * @Description:    展示所有设备分类管理
     * @Author:         杜凯之
     * @CreateDate:     2018/8/31 17:29
     * @Version:        1.0
     */
    public List<SbglSbflgl> sbglSbflglList(Pagination page, @Param("entity") SbglSbflgl entity);
}
