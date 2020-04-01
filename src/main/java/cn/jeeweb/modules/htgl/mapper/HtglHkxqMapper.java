package cn.jeeweb.modules.htgl.mapper;

import cn.jeeweb.modules.htgl.entity.HtglHkxq;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HtglHkxqMapper extends BaseMapper<HtglHkxq> {
    public List<HtglHkxq> ajaxHkxqList(Pagination page, @Param("entity") HtglHkxq entity);

}
