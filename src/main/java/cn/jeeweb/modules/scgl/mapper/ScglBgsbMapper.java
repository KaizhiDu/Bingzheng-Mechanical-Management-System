package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.dto.BgsbDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgsb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 包工 - 包工设备
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/26 15:52
 */
public interface ScglBgsbMapper extends BaseMapper<ScglBgsb> {

    /**
     * Dscription: 包工 - 添加的设备展示
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 16:15
     */
    public List<BgsbDTO> ajaxBgrwfpSbList(Pagination page, @Param("entity") BgsbDTO entity);
}
