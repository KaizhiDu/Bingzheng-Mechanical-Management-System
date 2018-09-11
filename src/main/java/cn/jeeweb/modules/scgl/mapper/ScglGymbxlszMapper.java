package cn.jeeweb.modules.scgl.mapper;

import cn.jeeweb.modules.scgl.entity.ScglGymbxlsz;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description:    工艺小类设置
 * @Author:         杜凯之
 * @CreateDate:     2018/9/11 9:48
 * @Version:        1.0
 */
public interface ScglGymbxlszMapper  extends BaseMapper<ScglGymbxlsz> {
    /**
     * @Description:    工艺模板小类信息展示
     * @Author:         杜凯之
     * @CreateDate:     2018/9/11 10:01
     * @Version:        1.0
     */
    public List<ScglGymbxlsz> gymbxlszList(Pagination page, @Param("entity") ScglGymbxlsz entity);
}
