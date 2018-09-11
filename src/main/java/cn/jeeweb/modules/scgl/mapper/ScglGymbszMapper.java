package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.entity.ScglGymbsz;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    生产管理-工艺模板设置
 * @Author:         杜凯之
 * @CreateDate:     2018/9/5 14:21
 * @Version:        1.0
 */
public interface ScglGymbszMapper extends BaseMapper<ScglGymbsz> {
    /**
     * @Description:    展示所有工艺大类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/8/18 16:58
     * @Version:        1.0
     */
    public List<ScglGymbsz> gymbszList(Pagination page, @Param("entity") ScglGymbsz entity);
}
