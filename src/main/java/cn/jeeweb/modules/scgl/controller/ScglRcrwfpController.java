package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.dto.YgsjDTO;
import cn.jeeweb.modules.scgl.entity.ScglRcrwfp;
import cn.jeeweb.modules.scgl.entity.ScglRggs;
import cn.jeeweb.modules.scgl.service.IScglRcrwfpService;
import cn.jeeweb.modules.scgl.service.IScglRggsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 生产管理-日常任务分配
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 17:04
 */
@Controller
@RequestMapping("${admin.url.prefix}/scgl/rcrwfp")
@RequiresPathPermission("scgl:rcrwfp")
public class ScglRcrwfpController extends BaseCRUDController<ScglRcrwfp, String> {

    /**生产管理-日常任务分配*/
    @Autowired
    private IScglRcrwfpService scglRcrwfpService;

    @Autowired
    /**生产管理-日工工时*/
    private IScglRggsService scglRggsService;

    /**
     * Dscription: 添加日子和搜索项
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 17:14
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        //得到当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentTime = sdf.format(date);
        model.addAttribute("currentTime",currentTime);

        //判断表里面有没有该数据
        EntityWrapper<ScglRcrwfp> wrapper = new EntityWrapper<ScglRcrwfp>();
        wrapper.eq("RQ",currentTime);
        List<ScglRcrwfp> scglRcrwfps = scglRcrwfpService.selectList(wrapper);
        //如果表里面没有就插入数据
        if (scglRcrwfps.size()==0){
            List<ScglRcrwfp> list = new ArrayList<ScglRcrwfp>();
            List<YgsjDTO> ygsjList = scglRcrwfpService.getYgsj();
            for (YgsjDTO ygsjDTO: ygsjList) {
                ScglRcrwfp s = new ScglRcrwfp();
                s.setXb(ygsjDTO.getXb());
                s.setRq(currentTime);
                s.setXm(ygsjDTO.getXm());
                s.setZw(ygsjDTO.getZw());
                s.setYgid(ygsjDTO.getYgid());
                list.add(s);
            }
            scglRcrwfpService.insertBatch(list);
        }
    }

    /**
     * Dscription: 得到所有当天的员工的日常任务分配
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 18:50
     */
    @RequestMapping(value = "ajaxRcrwfpList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScglRcrwfp> ajaxRcrwfpList(Queryable queryable, ScglRcrwfp scglRcrwfp, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<ScglRcrwfp> pageJson = scglRcrwfpService.ajaxRcrwfpList(queryable,scglRcrwfp);
        return pageJson;
    }

    /**
     * Dscription: 跳转到日常任务分配页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/25 11:07
     */
    @RequestMapping(value = "fprw", method={RequestMethod.GET, RequestMethod.POST})
    public String fprw(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRcrwfp scglRcrwfp = scglRcrwfpService.selectById(id);
        model.addAttribute("rcrwfp",scglRcrwfp);
        return display("fprw");
    }

    /**
     * Dscription: 跳转到分配工时页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/25 11:44
     */
    @RequestMapping(value = "fpgs", method={RequestMethod.GET, RequestMethod.POST})
    public String fpgs(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        ScglRcrwfp scglRcrwfp = scglRcrwfpService.selectById(id);
        model.addAttribute("rcrwfp",scglRcrwfp);
        EntityWrapper<ScglRggs> wrapper = new EntityWrapper<ScglRggs>();
        wrapper.eq("RCRWFPID", scglRcrwfp.getId());
        ScglRggs scglRggs = scglRggsService.selectOne(wrapper);
        model.addAttribute("rggs", scglRggs);
        if (scglRggs==null){
            model.addAttribute("RggsId", "");
        }
        else{
            model.addAttribute("RggsId", scglRggs.getId());
        }
        return display("fpgs");
    }

    /**
     * Dscription: 保存工时
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/26 9:27
     */
    @RequestMapping(value = "saveGs", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveGs(ScglRggs scglRggs, HttpServletRequest request, HttpServletResponse response, Model model){
        if (scglRggs.getGsmc()==null){
            scglRggs.setGsmc("");
        }
        //执行插入操作
        if (scglRggs.getId()==null||scglRggs.getId().equals("")){
            scglRggs.setId(null);
            scglRggsService.insert(scglRggs);
        }
        //执行更新操作
        else{
            scglRggsService.updateById(scglRggs);
        }
    }
}
