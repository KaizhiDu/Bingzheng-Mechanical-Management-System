package cn.jeeweb.modules.zjls.mapper;

import cn.jeeweb.modules.zjls.entity.ZjlsZjls;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 资金流水
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/2/17 18:36
 */
public interface ZjlsZjlsMapper extends BaseMapper<ZjlsZjls>{
    public List<ZjlsZjls> ajaxZjlsList(Pagination page, @Param("n") String n, @Param("y") String y, @Param("lx") String lx, @Param("r") String r, @Param("mx2") String mx2);

    public List<ZjlsZjls> dc(@Param("entity") ZjlsZjls entity);
}
