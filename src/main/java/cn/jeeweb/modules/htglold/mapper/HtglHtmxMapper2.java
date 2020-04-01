package cn.jeeweb.modules.htglold.mapper;

import cn.jeeweb.modules.htglold.entity.HtglHtmx2;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 合同管理 - 合同明细
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/11 13:40
 */
public interface HtglHtmxMapper2 extends BaseMapper<HtglHtmx2>{

    public List<HtglHtmx2> ajaxHtmxList(Pagination page, @Param("htid") String htid, @Param("lx") String lx);
}
