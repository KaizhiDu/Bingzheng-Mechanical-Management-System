package cn.jeeweb.modules.scjhgl.mapper;

import cn.jeeweb.modules.scgl.dto.SsxDTO;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    生产计划管理-零部件管理
 * @Author:         杜凯之
 * @CreateDate:     2018/9/4 17:07
 * @Version:        1.0
 */
public interface ScjhglLjglMapper extends BaseMapper<ScjhglLjgl> {

    /**
     * @Description:    展示所有零部件信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    public List<ScjhglLjgl> ajaxljglList(Pagination page, @Param("entity") ScjhglLjgl entity);

    /**
     * @Description:    展示所有零部件信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    public List<ScjhglLjgl> ajaxlbjglList(Pagination page, @Param("entity") ScjhglLjgl entity);

    /**
     * Dscription: 根据计划ID获取所有的零部件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 12:55
     */
    public List<SsxDTO> cxLj(@Param("jhid") String jhid);

    public List<ScjhglLjgl> getLjByjhid(@Param("jhid") String jhid);
}
