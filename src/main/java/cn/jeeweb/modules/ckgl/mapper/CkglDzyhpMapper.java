package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglDzyhp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 低值易耗品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 12:17
 */
public interface CkglDzyhpMapper extends BaseMapper<CkglDzyhp>{
    /**
     * Dscription: 保存低值易耗品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 16:59
     */
    public List<CkglDzyhp> ajaxDzyhpList(Pagination page, @Param("entity") CkglDzyhp entity);
}
