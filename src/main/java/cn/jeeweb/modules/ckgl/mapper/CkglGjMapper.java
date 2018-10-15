package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglGj;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 工具
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 19:03
 */
public interface CkglGjMapper extends BaseMapper<CkglGj>{

    /**
     * Dscription: 展示所有工具的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public List<CkglGj> ajaxGjList(Pagination page, @Param("entity") CkglGj entity);

}
