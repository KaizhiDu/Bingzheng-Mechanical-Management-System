package cn.jeeweb.modules.ckgl.mapper;

import cn.jeeweb.modules.ckgl.entity.CkglYcl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 仓库管理 - 原材料
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/14 15:09
 */
public interface CkglYclMapper extends BaseMapper<CkglYcl> {

    /**
     * Dscription: 展示所有标准件的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/13 19:32
     */
    public List<CkglYcl> ajaxYclList(Pagination page, @Param("entity") CkglYcl entity);
}
