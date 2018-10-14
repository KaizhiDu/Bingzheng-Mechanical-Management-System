package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglBzj;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 标准件
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/13 14:09
 */
public interface CkglBzjMapper extends BaseMapper<CkglBzj> {

    /**
     * Dscription: 展示所有标准件的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public List<CkglBzj> ajaxBzjList(Pagination page, @Param("entity") CkglBzj entity);
}
