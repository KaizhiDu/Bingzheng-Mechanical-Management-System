package cn.jeeweb.modules.jygl.mapper;

import cn.jeeweb.modules.jygl.entity.JyglBzjjy;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBzjgl;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 标准件检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/12/24 13:08
 */
public interface JyglBzjjyMapper extends BaseMapper<JyglBzjjy>{

    /**
     * Dscription: 导出标准件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/12/24 11:20
     */
    public List<JyglBzjjy> exportBzj(@Param("entity") JyglBzjjy entity);
}
