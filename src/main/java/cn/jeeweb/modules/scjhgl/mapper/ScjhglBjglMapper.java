package cn.jeeweb.modules.scjhgl.mapper;

import cn.jeeweb.modules.scjhgl.entity.ScjhglBjgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 生产计划管理 - 部件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/8 13:06
 */
public interface ScjhglBjglMapper extends BaseMapper<ScjhglBjgl> {
    /**
     * Dscription: 展示所有部件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/8 17:06
     */
    public List<ScjhglBjgl> ajaxBjglList(Pagination page, @Param("entity") ScjhglBjgl entity);
}
