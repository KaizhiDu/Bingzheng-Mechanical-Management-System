package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglXl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 小类
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/13 9:48
 */
public interface CkglXlMapper extends BaseMapper<CkglXl> {

    /**
     * Dscription: 根据大类id查到下属工艺小类的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 12:55
     */
    public List<CkglXl> ajaxCkxlList(Pagination page, @Param("entity") CkglXl entity);
}
