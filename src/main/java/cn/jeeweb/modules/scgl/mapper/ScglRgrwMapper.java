package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.dto.RgrwDTO;
import cn.jeeweb.modules.scgl.entity.ScglRgrw;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 日工 - 任务
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/27 14:01
 */
public interface ScglRgrwMapper extends BaseMapper<ScglRgrw> {

    /**
     * Dscription: 展示所以日工任务
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/27 14:36
     */
    public List<RgrwDTO> ajaxRcrwfpRwList(Pagination page, @Param("entity") RgrwDTO entity);
}
