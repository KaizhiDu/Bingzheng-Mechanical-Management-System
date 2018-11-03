package cn.jeeweb.modules.scgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.dto.GybzglDTO;
import cn.jeeweb.modules.scgl.dto.SsxDTO;
import cn.jeeweb.modules.scgl.entity.ScglGybzgl;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGybzglService;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import cn.jeeweb.modules.scgl.service.IScglGymbszService;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Dscription: 生产管理-工艺编制概览
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/9/20 9:46
 */
@Controller
@RequestMapping("${admin.url.prefix}/scgl/gybzgl")
@RequiresPathPermission("scgl:gybzgl")
public class ScglGybzglController extends BaseCRUDController<ScglGybzgl, String> {

    /**生产管理-工艺编制概览Service*/
    @Autowired
    private IScglGybzglService scglGybzglService;

    /**生产计划管理-计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**生产计划管理-零部件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**生产管理-工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

    /**生产管理-工艺小类编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**
     * Dscription: 搜索项
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 10:46
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        //得到所有计划信息
        List<SsxDTO> jhglList = scjhglHtglService.getJhList();
        model.addAttribute("jhglList", jhglList);
    }

    /**
     * Dscription: 展示所有工艺编制概览
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 10:09
     */
    @RequestMapping(value = "ajaxGybzglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<GybzglDTO> ajaxGybzglList(Queryable queryable, GybzglDTO gybzglDTO, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<GybzglDTO> pageJson = scglGybzglService.ajaxGybzglList(queryable,gybzglDTO);
        return pageJson;
    }

    /**
     * Dscription: 根据计划ID查所有下属零部件
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 11:02
     */
    @RequestMapping(value = "cxLj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<SsxDTO> cxLj(String jhid, HttpServletResponse response, HttpServletRequest request, Model model){
        List<SsxDTO> ljList = scjhglLjglService.cxLj(jhid);
        return ljList;
    }

    /**
     * Dscription: 根据零部件ID查到所有下属工艺大类
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 11:19
     */
    @RequestMapping(value = "cxGydl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<SsxDTO> cxGydl(String ljid, HttpServletResponse response, HttpServletRequest request, Model model){
        List<SsxDTO> gydlList = scglGydlbzService.cxGydl(ljid);
        return gydlList;
    }

    /**
     * Dscription: 根据编制大类ID查到所有下属的编制小类
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/9/20 11:19
     */
    @RequestMapping(value = "cxGyxl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<ScglLjgybz> cxGyxl(String gydlbzid, HttpServletResponse response, HttpServletRequest request, Model model){
        EntityWrapper<ScglLjgybz> wrapper = new EntityWrapper<ScglLjgybz>();
        wrapper.eq("gydlbzid",gydlbzid);
        List<ScglLjgybz> gyxlList = scglLjgybzService.selectList(wrapper);
        return gyxlList;
    }
}
