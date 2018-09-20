package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.dto.GybzglDTO;
import cn.jeeweb.modules.scgl.entity.ScglGybzgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 生产管理-工艺编制概览
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 9:46
 */
public interface ScglGybzglMapper extends BaseMapper<ScglGybzgl> {

    /**
     * Dscription: 展示所有工艺编制概览
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 10:09
     */
    public List<GybzglDTO> ajaxGybzglList(Pagination page, @Param("entity") GybzglDTO entity);
}
