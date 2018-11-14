package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglYclMx;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 原材料明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 15:40
 */
public interface CkglYclMxMapper extends BaseMapper<CkglYclMx> {
    public List<CkglYclMx> ajaxXqList(Pagination page, @Param("entity") CkglYclMx entity);
}
