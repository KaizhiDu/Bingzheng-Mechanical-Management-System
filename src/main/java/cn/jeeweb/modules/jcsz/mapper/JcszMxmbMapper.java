package cn.jeeweb.modules.jcsz.mapper;

import cn.jeeweb.modules.jcsz.entity.JcszMxmb;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 基础数据 - 资金数额
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2019/1/3 15:38
 */
public interface JcszMxmbMapper extends BaseMapper<JcszMxmb>{
    /**
     * Dscription: 展示所有明细模板信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2019/1/4 15:31
     */
    public List<JcszMxmb> ajaxMxmbList(Pagination page, @Param("entity") JcszMxmb entity);
}
