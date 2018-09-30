package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.RgpgJcxxDTO;
import cn.jeeweb.modules.scgl.dto.RgpgdDTO;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.ScglRcrwfp;
import cn.jeeweb.modules.scgl.mapper.ScglRcrwfpMapper;
import cn.jeeweb.modules.scgl.service.IScglRcrwfpService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 生产管理-日常任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 17:04
 */
@Transactional
@Service("IScglRcrwfpService")
public class ScglRcrwfpServiceImpl extends CommonServiceImpl<ScglRcrwfpMapper, ScglRcrwfp> implements IScglRcrwfpService {

    /**生产管理-日常任务分配*/
    @Autowired
    private ScglRcrwfpMapper scglRcrwfpMapper;

    /**
     * Dscription: 得到员工数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:22
     */
    @Override
    public List<YgsjDTO> getYgsj() {
        return scglRcrwfpMapper.getYgsj();
    }

    /**
     * Dscription: 得到所有当天的员工的日常任务分配
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    @Override
    public PageJson<ScglRcrwfp> ajaxRcrwfpList(Queryable queryable, ScglRcrwfp scglRcrwfp) {
        //得到当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        Pageable pageable = queryable.getPageable();
        Page<ScglRcrwfp> page = new Page<ScglRcrwfp>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglRcrwfpMapper.ajaxRcrwfpList(page, scglRcrwfp, currentTime));
        PageJson<ScglRcrwfp> pagejson = new PageJson<ScglRcrwfp>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 获取派工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/29 14:00
     */
    @Override
    public List<RgpgJcxxDTO> getRgpgJcxx() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        return scglRcrwfpMapper.getRgpgJcxx(currentTime);
    }

    /**
     * Dscription: 获取最终派工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/29 14:00
     */
    @Override
    public List<RgpgdDTO> getRgpgd() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        return scglRcrwfpMapper.getRgpgd(currentTime);
    }
}
