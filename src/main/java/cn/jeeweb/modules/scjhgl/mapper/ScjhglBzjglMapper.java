package cn.jeeweb.modules.scjhgl.mapper;

import cn.jeeweb.modules.scjhgl.entity.ScjhglBzjgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 生产计划管理 - 标准件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/12/21 16:27
 */
public interface ScjhglBzjglMapper extends BaseMapper<ScjhglBzjgl>{

    /**
     * @Description:    展示所有标准件信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    public List<ScjhglBzjgl> ajaxBzjList(Pagination page, @Param("entity") ScjhglBzjgl entity);

    public List<ScjhglBzjgl> ajaxBzjGlList(Pagination page, @Param("entity") ScjhglBzjgl entity);

    /**
     * Dscription: 导出标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/24 11:20
     */
    public List<ScjhglBzjgl> exportBzj(@Param("entity") ScjhglBzjgl entity);



}


