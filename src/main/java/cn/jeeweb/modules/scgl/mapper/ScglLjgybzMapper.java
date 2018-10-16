package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    生产管理-零件工艺编制
 * @Author:         杜凯之
 * @CreateDate:     2018/9/14 15:58
 * @Version:        1.0
 */
public interface ScglLjgybzMapper extends BaseMapper<ScglLjgybz> {

    /**
     * @Description:    根据工艺大类编制ID获取所有小类信息
     * @Author:         杜凯之
     * @CreateDate:     2018/9/18 16:24
     * @Version:        1.0
     */
    public List<ScglLjgybz> ajaxGyxlbzList(Pagination page, @Param("entity") ScglLjgybz entity, @Param("gydlbzid") String gydlbzid);

    /**
     * Dscription: 通过零件ID，得到所有下属工艺信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/10 12:20
     */
    public List<ScglLjgybz> getLjgybzByLjid(@Param("ljid") String ljid);

    /**
     * Dscription: 通过计划ID, 得到所有下属工艺信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 12:58
     */
    public List<ScglLjgybz> getLjgybzByJhid(@Param("jhid") String jhid);

    /**
     * Dscription: 通过计划ID和工艺大类编制ID, 得到所有下属工艺信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 13:35
     */
    public List<ScglLjgybz> getLjgybzByJhidGydlid(@Param("jhid") String jhid, @Param("gydlid") String gydlid);
}
