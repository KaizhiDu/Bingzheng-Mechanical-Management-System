package cn.jeeweb.modules.jygl.mapper;

import cn.jeeweb.modules.jygl.dto.BgjyDTO;
import cn.jeeweb.modules.jygl.dto.BgjyxqDTO;
import cn.jeeweb.modules.jygl.entity.JyglBgjy;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Dscription: 检验管理 - 包工检验
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/1 10:54
 */
public interface JyglBgjyMapper extends BaseMapper<JyglBgjy> {
    /**
     * Dscription: 展示所有包工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:14
     */
    public List<BgjyDTO> ajaxBgjyList(Pagination page, @Param("entity") BgjyDTO entity);

    public List<BgjyDTO> exportBgjyd(@Param("xm") String xm, @Param("rq") String rq);

    /**
     * Dscription: 展示所有包工详情信息（展示）
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:14
     */
    public List<BgjyxqDTO> ajaxBgjyxqList(Pagination page, @Param("entity") BgjyxqDTO entity, @Param("ID") String id);

    /**
     * Dscription: 展示所有包工详情信息（数据处理）
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/1 11:14
     */
    public List<BgjyxqDTO> bgjyxqList(@Param("entity") BgjyxqDTO entity, @Param("ID") String id);
}
