package cn.jeeweb.modules.htglold.mapper;

import cn.jeeweb.modules.htglold.entity.HtglHt;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 合同管理 - 合同
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/10 13:37
 */
public interface HtglHtMapper extends BaseMapper<HtglHt>{

    public List<HtglHt> ajaxHtList(Pagination page, @Param("entity") HtglHt entity);
}
