package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 未完成半成品
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 13:24
 */
public interface CkglBcpMapper extends BaseMapper<CkglBcp> {

    /**
     * Dscription: 查所有已完成或者未完成的半成品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:17
     */
    public List<CkglBcp> ajaxBcpList(Pagination page, @Param("entity") CkglBcp entity);

    /**
     * Dscription: 查所有未完成的半成品
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 15:17
     */
    public List<CkglBcp> ajaxBcpList2(Pagination page, @Param("entity") CkglBcp entity);
}
