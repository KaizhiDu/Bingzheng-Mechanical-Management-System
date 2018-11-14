package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglRjMx;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 刃具明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/15 9:22
 */
public interface CkglRjMxMapper extends BaseMapper<CkglRjMx>{
    public List<CkglRjMx> ajaxXqList(Pagination page, @Param("entity") CkglRjMx entity);
}
