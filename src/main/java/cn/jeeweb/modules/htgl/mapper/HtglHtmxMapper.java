package cn.jeeweb.modules.htgl.mapper;

import cn.jeeweb.modules.htgl.entity.HtglHtmx;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HtglHtmxMapper extends BaseMapper<HtglHtmx> {

    public List<HtglHtmx> ajaxHtmxList(Pagination page, @Param("entity") HtglHtmx entity);

}
