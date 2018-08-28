package cn.jeeweb.modules.grgl.mapper;

import cn.jeeweb.modules.grgl.dto.YgzxxDTO;
import cn.jeeweb.modules.grgl.entity.Xzzwfp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description:    员工薪资职位分配
* @Author:         杜凯之
* @CreateDate:     2018/8/28 9:32
* @Version:        1.0
*/
public interface GrglXzzwfpMapper extends BaseMapper<Xzzwfp> {

    /**
     * @Description:    查出所有员工的职位薪资分配
     * @Author:         杜凯之
     * @CreateDate:     2018/8/28 14:51
     * @Version:        1.0
     */
    public List<YgzxxDTO> xzzwfpList(Pagination page, @Param("xzzwfp") Xzzwfp entity);
}
