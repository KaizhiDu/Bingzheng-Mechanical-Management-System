package cn.jeeweb.modules.zzgl.mapper;

import cn.jeeweb.modules.zzgl.dto.ZzglDTO;
import cn.jeeweb.modules.zzgl.entity.ZzglZzgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 资金管理 - 资金管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/5 9:33
 */
public interface ZzglZzglMapper extends BaseMapper<ZzglZzgl>{
    /**
     * Dscription: 展示所有资金管理信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/5 14:47
     */
    public List<ZzglZzgl> ajaxZzglList(Pagination page, @Param("n") String n, @Param("y") String y, @Param("px") String px, @Param("lx") String lx, @Param("r") String r);

    public List<ZzglZzgl> exportZzgl(@Param("n") String n, @Param("y") String y, @Param("r") String r, @Param("px") String px);
}
