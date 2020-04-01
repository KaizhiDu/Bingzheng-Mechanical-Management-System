package cn.jeeweb.modules.htgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.htgl.entity.HtglGsht;
import cn.jeeweb.modules.htgl.entity.HtglHtgl;
import cn.jeeweb.modules.htgl.entity.HtglHtmx;
import cn.jeeweb.modules.htgl.entity.HtglHzgs;
import cn.jeeweb.modules.htgl.service.IHtglHtglService;
import cn.jeeweb.modules.htgl.service.IHtglHtmxService;
import cn.jeeweb.modules.htgl.service.IHtglHzgsService;
import cn.jeeweb.modules.htglold.service.IHtglGsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Dscription: 合同管理- 公司
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2020/3/21 16:21
 */
@Controller
@RequestMapping("${admin.url.prefix}/htgl/hzgs")
@RequiresPathPermission("htgl:hzgs")
public class HtglHzgsController extends BaseCRUDController<HtglHzgs, String> {

    @Autowired
    private IHtglHzgsService htglHzgsService;

    @Autowired
    private IHtglHtglService htglhtglService;

    @Autowired
    private IHtglHtmxService htglHtmxService;

    @RequestMapping(value = "tjgs", method={RequestMethod.GET, RequestMethod.POST})
    public String tjgs(HttpServletRequest request, HttpServletResponse response){
        return display("tjgs");
    }

    @RequestMapping(value = "saveHzgs", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveHzgs(HtglHzgs htglHzgs, HttpServletRequest request, HttpServletResponse response, Model model) {
        htglHzgsService.insert(htglHzgs);
    }

    @RequestMapping(value = "htxq", method={RequestMethod.GET, RequestMethod.POST})
    public String htxq(String gsid, HttpServletRequest request, HttpServletResponse response,  Model model){
        String gsmc = htglHzgsService.selectById(gsid).getMc();
        EntityWrapper<HtglHtgl> wrapper = new EntityWrapper<>();
        wrapper.eq("ssgs", gsmc);
        Double gsje = 0.0;
        Double gsfp = 0.0;
        Double gshk = 0.0;
        List<HtglGsht> gshtList = new ArrayList<>();
        List<HtglHtgl> htglHtglList = htglhtglService.selectList(wrapper);
        for (HtglHtgl htglHtgl : htglHtglList) {
            gsje += htglHtgl.getJe();
            gsfp += htglHtgl.getFp();
            gshk += htglHtgl.getHk();
            HtglGsht htglGsht = new HtglGsht();
            htglGsht.setHth(htglHtgl.getHth());
            htglGsht.setMc(htglHtgl.getMc());
            htglGsht.setQdrq(htglHtgl.getQdrq());
            htglGsht.setQyhth(htglHtgl.getQyhth());
            htglGsht.setSfwc(true);
            EntityWrapper<HtglHtmx> wrapper1 = new EntityWrapper<>();
            wrapper1.eq("HTID", htglHtgl.getId());
            List<HtglHtmx> htglHtmxeList = htglHtmxService.selectList(wrapper1);
            for (HtglHtmx htglHtmx : htglHtmxeList) {
                if (htglHtmx.getSysl()>0) {
                    htglGsht.setSfwc(false);
                    break;
                }
            }
            gshtList.add(htglGsht);
        }
        model.addAttribute("gsmc", gsmc);
        model.addAttribute("gsje", gsje);
        model.addAttribute("gsfp", gsfp);
        model.addAttribute("gshk", gshk);
        model.addAttribute("gshtList", gshtList);

        return display("htxq");
    }

}
