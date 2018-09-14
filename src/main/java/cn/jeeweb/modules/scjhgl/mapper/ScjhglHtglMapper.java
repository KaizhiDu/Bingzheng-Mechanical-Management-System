package cn.jeeweb.modules.scjhgl.mapper;

import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    生产计划管理-合同管理
 * @Author:         杜凯之
 * @CreateDate:     2018/9/12 14:22
 * @Version:        1.0
 */
public interface ScjhglHtglMapper extends BaseMapper<ScjhglHtgl> {

    /**
     * @Description:    展示所有计划信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/14 17:01
     * @Version:        1.0
     */
    public List<ScjhglHtgl> ajaxJhglList(Pagination page, @Param("entity") ScjhglHtgl entity);
}
