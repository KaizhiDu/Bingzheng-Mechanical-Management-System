package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglBzjMx;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 标准件明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 13:30
 */
public interface CkglBzjMxMapper extends BaseMapper<CkglBzjMx> {

    public List<CkglBzjMx> ajaxXqList(Pagination page, @Param("entity") CkglBzjMx entity);
}
