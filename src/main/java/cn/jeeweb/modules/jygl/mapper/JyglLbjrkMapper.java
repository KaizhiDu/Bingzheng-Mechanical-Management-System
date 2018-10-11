package cn.jeeweb.modules.jygl.mapper;

import cn.jeeweb.modules.jygl.entity.JyglLbjrk;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 检验管理 - 零部件入库
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 16:34
 */
public interface JyglLbjrkMapper extends BaseMapper<JyglLbjrk> {

    /**
     * @Description:    展示所有零部件信息（除了未完成入库的零部件）
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    public List<JyglLbjrk> ajaxlbjglList(Pagination page, @Param("entity") JyglLbjrk entity);
}
