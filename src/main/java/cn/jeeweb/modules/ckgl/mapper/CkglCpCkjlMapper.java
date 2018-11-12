package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglCpCkjl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 成品出库记录
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/26 14:28
 */
public interface CkglCpCkjlMapper extends BaseMapper<CkglCpCkjl>{
    public List<CkglCpCkjl> ajaxCpxqList(Pagination page, @Param("entity") CkglCpCkjl entity);
}
