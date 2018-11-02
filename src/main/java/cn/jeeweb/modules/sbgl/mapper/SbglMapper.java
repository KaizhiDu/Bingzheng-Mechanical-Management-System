package cn.jeeweb.modules.sbgl.mapper;

import cn.jeeweb.modules.sbgl.entity.Sbgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbflgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbzy;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    设备管理
 * @Author:         杜凯之
 * @CreateDate:     2018/8/30 17:08
 * @Version:        1.0
 */
public interface SbglMapper extends BaseMapper<Sbgl> {
    /**
     * @Description:    展示所有设备
     * @Author:         杜凯之
     * @CreateDate:     2018/8/30 17:58
     * @Version:        1.0
     */
    public List<SbglSbzy> ajaxListSbgl(Pagination page, @Param("entity") SbglSbzy entity, @Param("addSb") String addSb);
}
