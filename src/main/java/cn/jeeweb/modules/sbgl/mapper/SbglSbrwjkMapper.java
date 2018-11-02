package cn.jeeweb.modules.sbgl.mapper;
import cn.jeeweb.modules.sbgl.entity.Sbgl;
import cn.jeeweb.modules.sbgl.entity.SbglSbrwjk;
import cn.jeeweb.modules.sbgl.entity.SbglSbzy;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    设备管理-设备任务监控
 * @Author:         杜凯之
 * @CreateDate:     2018/9/1 13:49
 * @Version:        1.0
 */
public interface SbglSbrwjkMapper extends BaseMapper<SbglSbrwjk> {
    /**
     * @Description:    展示所有设备监控情况
     * @Author:         杜凯之
     * @CreateDate:     2018/8/30 17:58
     * @Version:        1.0
     */
    public List<SbglSbzy> ajaxListSbrwjk(Pagination page, @Param("entity") SbglSbzy entity);
}
