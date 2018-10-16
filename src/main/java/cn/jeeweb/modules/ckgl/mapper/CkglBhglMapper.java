package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.dto.CkglBhglDTO;
import cn.jeeweb.modules.ckgl.entity.CkglBhgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 补货管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/16 9:37
 */
public interface CkglBhglMapper extends BaseMapper<CkglBhgl>{

    /**
     * Dscription: 展示所有需要补货的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public List<CkglBhglDTO> ajaxBhglList(Pagination page, @Param("entity") CkglBhglDTO entity);

    /**
     * Dscription: 导出
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 10:46
     */
    public List<CkglBhglDTO> bhglList();
}
