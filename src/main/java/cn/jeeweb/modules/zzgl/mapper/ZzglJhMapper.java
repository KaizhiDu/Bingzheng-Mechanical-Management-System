package cn.jeeweb.modules.zzgl.mapper;

import cn.jeeweb.modules.zzgl.entity.ZzglJh;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 资金管理 - 借还
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/7 14:47
 */
public interface ZzglJhMapper extends BaseMapper<ZzglJh>{
    public List<ZzglJh> ajaxJhList(Pagination page, @Param("n") String n, @Param("y") String y, @Param("name") String name, @Param("r") String r);

}
