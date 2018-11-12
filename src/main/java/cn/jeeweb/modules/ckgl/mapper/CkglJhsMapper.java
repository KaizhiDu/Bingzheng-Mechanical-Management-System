package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglJhs;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 进货商
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/11/12 13:02
 */
public interface CkglJhsMapper extends BaseMapper<CkglJhs>{

    public List<CkglJhs> ajaxJhsList(Pagination page, @Param("entity") CkglJhs entity);
}
