package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglRj;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 刃具
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 9:13
 */
public interface CkglRjMapper extends BaseMapper<CkglRj>{

    /**
     * Dscription: 展示所有刃具的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public List<CkglRj> ajaxRjList(Pagination page, @Param("entity") CkglRj entity);
}
