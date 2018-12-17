package cn.jeeweb.modules.scgl.service.impl;

import cn.jeeweb.core.common.service.impl.CommonServiceImpl;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Pageable;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.modules.scgl.dto.BgpgJcxxDTO;
import cn.jeeweb.modules.scgl.dto.BgpgdDTO;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.ScglBgrwfp;
import cn.jeeweb.modules.scgl.mapper.ScglBgrwfpMapper;
import cn.jeeweb.modules.scgl.service.IScglBgrwfpService;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 生产管理-包工任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/28 12:19
 */
@Transactional
@Service("IScglBgrwfpService")
public class ScglBgrwfpServiceImpl extends CommonServiceImpl<ScglBgrwfpMapper, ScglBgrwfp> implements IScglBgrwfpService {

    /**生产管理-日常任务分配*/
    @Autowired
    private ScglBgrwfpMapper scglBgrwfpMapper;

    /**
     * Dscription: 得到员工数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:22
     */
    @Override
    public List<YgsjDTO> getYgsj() {
        return scglBgrwfpMapper.getYgsj();
    }

    /**
     * Dscription: 得到所有当天的员工的包工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    @Override
    public PageJson<ScglBgrwfp> ajaxBgrwfpList(Queryable queryable, ScglBgrwfp scglBgrwfp) {
        //得到当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        Pageable pageable = queryable.getPageable();
        Page<ScglBgrwfp> page = new Page<ScglBgrwfp>(pageable.getPageNumber(), pageable.getPageSize());
        page.setRecords(scglBgrwfpMapper.ajaxBgrwfpList(page, scglBgrwfp, currentTime));
        PageJson<ScglBgrwfp> pagejson = new PageJson<ScglBgrwfp>(pageable.getPageNumber(), page.getSize(), page.getTotal(), page.getRecords());
        return pagejson;
    }

    /**
     * Dscription: 得到包工派工信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 10:49
     */
    @Override
    public List<BgpgdDTO> getBgpgd(String rq){
        return scglBgrwfpMapper.getBgpgd(rq);
    }

    /**
     * Dscription: 得到包工派工基础数据
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/30 10:55
     */
    @Override
    public List<BgpgJcxxDTO> getBgpgJcxx(String rq, String ygid) {
        return scglBgrwfpMapper.getBgpgJcxx(rq, ygid);
    }
}
