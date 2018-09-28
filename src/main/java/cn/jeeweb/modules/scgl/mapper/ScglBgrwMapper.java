package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.dto.BgrwDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgrw;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 包工 - 任务
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/27 14:01
 */
public interface ScglBgrwMapper extends BaseMapper<ScglBgrw> {

    /**
     * Dscription: 展示所有包工任务
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 14:33
     */
    public List<BgrwDTO> ajaxBgrwfpRwList(Pagination page, @Param("entity") BgrwDTO entity);
}
