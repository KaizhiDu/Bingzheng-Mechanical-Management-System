package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    生产管理-零件工艺编制
 * @Author:         杜凯之
 * @CreateDate:     2018/9/14 15:58
 * @Version:        1.0
 */
public interface ScglLjgybzMapper extends BaseMapper<ScglLjgybz> {

    /**
     * @Description:    根据工艺大类编制ID获取所有小类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/18 16:24
     * @Version:        1.0
     */
    public List<ScglLjgybz> ajaxGyxlbzList(Pagination page, @Param("entity") ScglLjgybz entity, @Param("gydlbzid") String gydlbzid);
}
