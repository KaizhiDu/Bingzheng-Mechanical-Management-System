package cn.jeeweb.modules.htgl.mapper;

import cn.jeeweb.modules.htgl.entity.HtglFpxq;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HtglFpxqMapper extends BaseMapper<HtglFpxq> {
    public List<HtglFpxq> ajaxFpxqList(Pagination page, @Param("entity") HtglFpxq entity);

}
