package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.dto.RgsbDTO;
import cn.jeeweb.modules.scgl.entity.ScglRgsb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 日工 - 日工设备
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/26 15:52
 */
public interface ScglRgsbMapper extends BaseMapper<ScglRgsb> {
    /**
     * Dscription: 日工 - 添加的设备展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 16:15
     */
    public List<RgsbDTO> ajaxRcrwfpSbList(Pagination page, @Param("entity") RgsbDTO entity);
}
