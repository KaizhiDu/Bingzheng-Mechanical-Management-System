package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.dto.GydlbzDTO;
import cn.jeeweb.modules.scgl.dto.SsxDTO;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    生产管理-工艺大类编制
 * @Author:         杜凯之
 * @CreateDate:     2018/9/14 18:03
 * @Version:        1.0
 */
public interface ScglGydlbzMapper extends BaseMapper<ScglGydlbz> {
    /**
     * @Description:    根据计划id得到所有大类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/17 9:30
     * @Version:        1.0
     */
    public List<GydlbzDTO> ajaxGydlbzList(Pagination page, @Param("entity") GydlbzDTO gydlbzDTO);

    /**
     * Dscription: 根据根据零件id得到所有大类信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 13:13
     */
    public List<SsxDTO> cxGydl(@Param("ljid") String ljid);
}
