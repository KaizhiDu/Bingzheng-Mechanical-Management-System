package cn.jeeweb.modules.htgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.htgl.entity.*;
import cn.jeeweb.modules.htgl.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Dscription: 合同管理- 合同管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2020/3/21 15:21
 */

@Controller
@RequestMapping("${admin.url.prefix}/htgl/htgl")
@RequiresPathPermission("htgl:htgl")
public class HtglHtglController extends BaseCRUDController<HtglHtgl, String> {

    @Autowired
    private IHtglHzgsService htglHzgsService;

    @Autowired
    private IHtglHtglService htglHtglService;

    @Autowired
    private IHtglHtmxService htglHtmxService;

    @Autowired
    private IHtglWcqkService htglWcqkService;

    @Autowired
    private IHtglFpxqService htglFpxqService;

    @Autowired
    private IHtglHkxqService htglHkxqService;

    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        List<HtglHzgs> htglHzgsList = htglHzgsService.selectList(null);
        model.addAttribute("gsList", htglHzgsList);
    }

    @RequestMapping(value = "ajaxHtglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<HtglHtgl> ajaxHtglList(Queryable queryable, HtglHtgl htglHtgl, HttpServletRequest request, HttpServletResponse response, Model model){
       PageJson<HtglHtgl> pageJson = htglHtglService.ajaxHtglList(queryable, htglHtgl);
        return pageJson;
    }

    @RequestMapping(value = "tjht", method={RequestMethod.GET, RequestMethod.POST})
    public String tjht(HttpServletRequest request, HttpServletResponse response, Model model) {
        List<HtglHzgs> htglHzgsList = htglHzgsService.selectList(null);
        model.addAttribute("gsList", htglHzgsList);
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("currentDate", currentDate);
        return display("tjht");
    }

    @RequestMapping(value = "saveHt", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveHt(HtglHtgl htglHzgl, HttpServletRequest request, HttpServletResponse response, Model model) {
        htglHzgl.setJe(0.0);
        htglHzgl.setFp(0.0);
        htglHzgl.setHk(0.0);
        htglHtglService.insert(htglHzgl);
    }

    @RequestMapping(value = "htmx", method={RequestMethod.GET, RequestMethod.POST})
    public String htmx(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("id", id);
        return display("htmx");
    }

    @RequestMapping(value = "tjhtmx", method={RequestMethod.GET, RequestMethod.POST})
    public String tjhtmx(String htid, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("htid", htid);
        return display("tjhtmx");
    }

    @RequestMapping(value = "saveHtmx", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveHt(HtglHtmx htglHzmx, HttpServletRequest request, HttpServletResponse response, Model model) {
        htglHzmx.setSysl(htglHzmx.getSl());
        htglHtmxService.insert(htglHzmx);
        Double je = htglHzmx.getJe();
        HtglHtgl htglHtgl = htglHtglService.selectById(htglHzmx.getHtid());
        htglHtgl.setHk(htglHtgl.getHk() + je);
        htglHtgl.setJe(htglHtgl.getJe() + je);
        htglHtgl.setFp(htglHtgl.getFp() + je);
        htglHtglService.updateById(htglHtgl);
    }

    @RequestMapping(value = "deleteHt", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteHt(String ids, HttpServletRequest request, HttpServletResponse response, Model model) {
        String idArray[] = ids.split(",");
        for (String id : idArray) {
            htglHtglService.deleteById(id);
        }
    }

    @RequestMapping(value = "ajaxHtmxList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<HtglHtmx> ajaxHtmxList(Queryable queryable, String htid, HtglHtmx htglHtmx, HttpServletRequest request, HttpServletResponse response, Model model){
        htglHtmx.setHtid(htid);
        PageJson<HtglHtmx> pageJson = htglHtmxService.ajaxHtmxList(queryable, htglHtmx);
        return pageJson;
    }

    @RequestMapping(value = "deleteHtmx", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteHtmx(String ids, String htid, HttpServletRequest request, HttpServletResponse response, Model model) {
        String idArray[] = ids.split(",");
        for (String id : idArray) {
            Double je = htglHtmxService.selectById(id).getJe();
            HtglHtgl htglHtgl = htglHtglService.selectById(htid);
            htglHtgl.setJe(htglHtgl.getJe() - je);
            htglHtgl.setFp(htglHtgl.getFp() - je);
            htglHtgl.setHk(htglHtgl.getHk() - je);
            htglHtglService.updateById(htglHtgl);
            htglHtmxService.deleteById(id);
        }
    }

    @RequestMapping(value = "htjd", method={RequestMethod.GET, RequestMethod.POST})
    public String htjd(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("id", id);
        return display("htjd");
    }

    @RequestMapping(value = "wc", method={RequestMethod.GET, RequestMethod.POST})
    public String wc(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("htmxId", id);
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("currentDate", currentDate);
        return display("wc");
    }

    @RequestMapping(value = "saveWc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveWc(HtglWcqk htglWcqk, HttpServletRequest request, HttpServletResponse response, Model model) {
        htglWcqkService.insert(htglWcqk);
        HtglHtmx htglHtmx = htglHtmxService.selectById(htglWcqk.getHtmxid());
        htglHtmx.setSysl(htglHtmx.getSysl() - htglWcqk.getWcsl());
        htglHtmxService.updateById(htglHtmx);
    }

    @RequestMapping(value = "wcqk", method={RequestMethod.GET, RequestMethod.POST})
    public String wcqk(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("htmxid", id);
        return display("wcqk");
    }

    @RequestMapping(value = "ajaxWcqkList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<HtglWcqk> ajaxWcqkList(Queryable queryable, String htmxid, HtglWcqk htglWcqk, HttpServletRequest request, HttpServletResponse response, Model model){
        htglWcqk.setHtmxid(htmxid);
        PageJson<HtglWcqk> pageJson = htglWcqkService.ajaxWcqkList(queryable, htglWcqk);
        return pageJson;
    }

    @RequestMapping(value = "deleteWcqk", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteWcqk(String ids, String htmxid, HttpServletRequest request, HttpServletResponse response, Model model) {
        String idArray[] = ids.split(",");
        for (String id : idArray) {
            Double wcsl = htglWcqkService.selectById(id).getWcsl();
            HtglHtmx htglHtmx = htglHtmxService.selectById(htmxid);
            htglHtmx.setSysl(htglHtmx.getSysl() + wcsl);
            htglHtmxService.updateById(htglHtmx);
            htglWcqkService.deleteById(id);
        }
    }

    @RequestMapping(value = "fp", method={RequestMethod.GET, RequestMethod.POST})
    public String fp(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        HtglHtgl htglHtgl = htglHtglService.selectById(id);
        model.addAttribute("ht", htglHtgl);
        model.addAttribute("htid", id);
        return display("fp");
    }

    @RequestMapping(value = "ajaxFpxqList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<HtglFpxq> ajaxFpxqList(Queryable queryable, String htid, HtglFpxq htglFpxq, HttpServletRequest request, HttpServletResponse response, Model model){
        htglFpxq.setHtid(htid);
        PageJson<HtglFpxq> pageJson = htglFpxqService.ajaxFpxqList(queryable, htglFpxq);
        return pageJson;
    }

    @RequestMapping(value = "kfp", method={RequestMethod.GET, RequestMethod.POST})
    public String kfp(String htid, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("htid", htid);
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("currentDate", currentDate);
        return display("kfp");
    }

    @RequestMapping(value = "saveFpxq", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveFpxq(HtglFpxq htglFpxq, HttpServletRequest request, HttpServletResponse response, Model model) {
        htglFpxqService.insert(htglFpxq);
        HtglHtgl htglHtgl = htglHtglService.selectById(htglFpxq.getHtid());
        htglHtgl.setFp(htglHtgl.getFp() - htglFpxq.getFpje());
        htglHtglService.updateById(htglHtgl);
    }

    @RequestMapping(value = "getFpje", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Double getFpje(String htid, HttpServletRequest request, HttpServletResponse response, Model model) {
        return htglHtglService.selectById(htid).getFp();
    }

    @RequestMapping(value = "ajaxHkxqList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<HtglHkxq> ajaxHkxqList(Queryable queryable, String htid, HtglHkxq htglHkxq, HttpServletRequest request, HttpServletResponse response, Model model){
        htglHkxq.setHtid(htid);
        PageJson<HtglHkxq> pageJson = htglHkxqService.ajaxHkxqList(queryable, htglHkxq);
        return pageJson;
    }

    @RequestMapping(value = "hk", method={RequestMethod.GET, RequestMethod.POST})
    public String hk(String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        HtglHtgl htglHtgl = htglHtglService.selectById(id);
        model.addAttribute("ht", htglHtgl);
        model.addAttribute("htid", id);
        return display("hk");
    }

    @RequestMapping(value = "getHkje", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Double getHkje(String htid, HttpServletRequest request, HttpServletResponse response, Model model) {
        return htglHtglService.selectById(htid).getHk();
    }

    @RequestMapping(value = "khk", method={RequestMethod.GET, RequestMethod.POST})
    public String khk(String htid, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("htid", htid);
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd");
        Date date0 = new Date();
        String currentDate = sdf0.format(date0);
        model.addAttribute("currentDate", currentDate);
        return display("khk");
    }

    @RequestMapping(value = "saveHkxq", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveHkxq(HtglHkxq htglHkxq, HttpServletRequest request, HttpServletResponse response, Model model) {
        htglHkxqService.insert(htglHkxq);
        HtglHtgl htglHtgl = htglHtglService.selectById(htglHkxq.getHtid());
        htglHtgl.setHk(htglHtgl.getHk() - htglHkxq.getHkje());
        htglHtglService.updateById(htglHtgl);
    }

    @RequestMapping(value = "deleteFp", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteFp(String ids, String htid, HttpServletRequest request, HttpServletResponse response, Model model) {
        String idArray[] = ids.split(",");
        for (String id : idArray) {
            HtglHtgl htglHtgl = htglHtglService.selectById(htid);
            Double fpje = htglFpxqService.selectById(id).getFpje();
            htglHtgl.setFp(htglHtgl.getFp() + fpje);
            htglHtglService.updateById(htglHtgl);
            htglFpxqService.deleteById(id);
        }
    }

    @RequestMapping(value = "deleteHk", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void deleteHk(String ids, String htid, HttpServletRequest request, HttpServletResponse response, Model model) {
        String idArray[] = ids.split(",");
        for (String id : idArray) {
            HtglHtgl htglHtgl = htglHtglService.selectById(htid);
            Double hkje = htglHkxqService.selectById(id).getHkje();
            htglHtgl.setHk(htglHtgl.getHk() + hkje);
            htglHtglService.updateById(htglHtgl);
            htglHkxqService.deleteById(id);
        }
    }

}
