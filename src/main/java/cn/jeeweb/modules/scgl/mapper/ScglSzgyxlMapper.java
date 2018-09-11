package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.entity.ScglGymbxlsz;
import cn.jeeweb.modules.scgl.entity.ScglSzgyxl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    生产管理-设置工艺小类
 * @Author:         杜凯之
 * @CreateDate:     2018/9/11 16:47
 * @Version:        1.0
 */
public interface ScglSzgyxlMapper extends BaseMapper<ScglSzgyxl> {

    /**
     * @Description:    显示设置工艺小类
     * @Author:         杜凯之
     * @CreateDate:     2018/9/11 18:45
     * @Version:        1.0
     */
    public List<ScglSzgyxl> szgyxlList(Pagination page, @Param("entity") ScglSzgyxl entity);
}
