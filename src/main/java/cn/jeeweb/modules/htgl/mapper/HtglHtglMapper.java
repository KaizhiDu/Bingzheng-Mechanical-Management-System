package cn.jeeweb.modules.htgl.mapper;

import cn.jeeweb.modules.htgl.entity.HtglHtgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 合同管理 - 合同管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/3/21 16:40
 */

public interface HtglHtglMapper extends BaseMapper<HtglHtgl> {

    public List<HtglHtgl> ajaxHtglList(Pagination page, @Param("entity") HtglHtgl entity);

}
