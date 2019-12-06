package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.dto.BgpgJcxxDTO;
import cn.jeeweb.modules.scgl.dto.BgpgdDTO;
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

    /**
     * Dscription: 得到包工派工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 10:52
     */
    public List<BgpgdDTO> getBgpgd(@Param("RQ") String rq);

    /**
     * Dscription: 得到包工派工基础数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 10:55
     */
    public List<BgpgJcxxDTO> getBgpgJcxx(@Param("id") String id);

    public List<BgpgJcxxDTO> getjyxx(@Param("id") String id);
}
