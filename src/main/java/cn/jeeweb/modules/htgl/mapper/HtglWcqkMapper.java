package cn.jeeweb.modules.htgl.mapper;

import cn.jeeweb.modules.htgl.entity.HtglWcqk;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HtglWcqkMapper extends BaseMapper<HtglWcqk> {

    public List<HtglWcqk> ajaxWcqkList(Pagination page, @Param("entity") HtglWcqk entity);

}
