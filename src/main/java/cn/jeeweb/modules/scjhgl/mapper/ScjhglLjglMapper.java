package cn.jeeweb.modules.scjhgl.mapper;

import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    生产计划管理-零件管理
 * @Author:         杜凯之
 * @CreateDate:     2018/9/4 17:07
 * @Version:        1.0
 */
public interface ScjhglLjglMapper extends BaseMapper<ScjhglLjgl> {

    /**
     * @Description:    展示所有零件信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    public List<ScjhglLjgl> ajaxljglList(Pagination page, @Param("entity") ScjhglLjgl entity);
}
