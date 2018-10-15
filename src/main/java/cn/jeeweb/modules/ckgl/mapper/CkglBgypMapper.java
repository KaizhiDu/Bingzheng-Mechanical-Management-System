package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglBgyp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 办公用品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 10:28
 */
public interface CkglBgypMapper extends BaseMapper<CkglBgyp>{

    /**
     * Dscription: 展示所有办公用品的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public List<CkglBgyp> ajaxBgypList(Pagination page, @Param("entity") CkglBgyp entity);
}
