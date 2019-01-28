package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglCp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/11 18:31
 */
public interface CkglCpMapper extends BaseMapper<CkglCp> {
    public List<CkglCp> ajaxCpList(Pagination page, @Param("entity") CkglCp entity);
}
