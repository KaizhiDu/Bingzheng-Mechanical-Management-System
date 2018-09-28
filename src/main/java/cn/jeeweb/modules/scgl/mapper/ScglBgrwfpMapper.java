package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgrwfp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 生产管理-包工任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/28 12:19
 */
public interface ScglBgrwfpMapper extends BaseMapper<ScglBgrwfp> {

    /**
     * Dscription: 得到员工数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:22
     */
    public List<YgsjDTO> getYgsj();

    /**
     * Dscription: 得到所有当天的员工的包工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    public List<ScglBgrwfp> ajaxBgrwfpList(Pagination page, @Param("entity") ScglBgrwfp entity, @Param("RQ") String rq);
}
