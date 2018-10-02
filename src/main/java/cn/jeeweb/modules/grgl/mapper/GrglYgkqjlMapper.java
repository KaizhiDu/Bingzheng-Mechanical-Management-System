package cn.jeeweb.modules.grgl.mapper;

import cn.jeeweb.modules.grgl.dto.YgkqjlDTO;
import cn.jeeweb.modules.grgl.entity.GrglYgkqjl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 员工管理 - 员工考勤记录
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/2 14:32
 */
public interface GrglYgkqjlMapper extends BaseMapper<GrglYgkqjl> {

    /**
     * Dscription: 展示所有考勤信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/2 16:29
     */
    public List<YgkqjlDTO> ajaxGrglYgkqjlList(Pagination page, @Param("entity") YgkqjlDTO entity);

}
