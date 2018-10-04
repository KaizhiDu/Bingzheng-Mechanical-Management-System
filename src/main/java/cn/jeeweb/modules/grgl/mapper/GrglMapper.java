package cn.jeeweb.modules.grgl.mapper;

import cn.jeeweb.modules.grgl.dto.YgzxxDTO;
import cn.jeeweb.modules.grgl.entity.Grgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description:    员工管理
* @Author:         杜凯之
* @CreateDate:     2018/8/18 15:23
* @Version:        1.0
*/
public interface GrglMapper extends BaseMapper<Grgl> {

    public List<Grgl> grglList(Pagination page, @Param("grgl") Grgl entity);

    public List<YgzxxDTO> ajaxListGrgl(Pagination page, @Param("grgl") YgzxxDTO entity);

}
