package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.dto.RgpgJcxxDTO;
import cn.jeeweb.modules.scgl.dto.RgpgdDTO;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.ScglRcrwfp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 生产管理-日常任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 17:04
 */
public interface ScglRcrwfpMapper extends BaseMapper<ScglRcrwfp> {

    /**
     * Dscription: 得到员工数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:22
     */
    public List<YgsjDTO> getYgsj();

    /**
     * Dscription: 得到所有当天的员工的日常任务分配
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    public List<ScglRcrwfp> ajaxRcrwfpList(Pagination page, @Param("entity") ScglRcrwfp entity, @Param("RQ") String rq);

    /**
     * Dscription: 获取派工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/29 14:00
     */
    public List<RgpgJcxxDTO> getRgpgJcxx(@Param("ID") String id, @Param("RQ") String currentTime);

    /**
     * Dscription: 获取最终派工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/29 14:00
     */
    public List<RgpgdDTO> getRgpgd(@Param("ID") String id, @Param("RQ") String currentTime);
}
