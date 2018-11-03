package cn.jeeweb.modules.sjfx.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import cn.jeeweb.modules.sjfx.dto.LbjDTO;
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
 * Dscription: 数据分析 - 零部件进度图
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/25 13:18
 */
@Controller
@RequestMapping("${admin.url.prefix}/sjfx/lbjjdt")
@RequiresPathPermission("sjfx:lbjjdt")
public class SjfxLbjjdtController extends BaseCRUDController<ScjhglLjgl, String> {

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**零部件管理*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**
     * @Description:    搜索项
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 17:22
     * @Version:        1.0
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
    }

    @RequestMapping(value = "checkJdt", method={RequestMethod.GET, RequestMethod.POST})
    public String checkJdt(String lbjid, Model model, HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("lbjid", lbjid);
        return display("checkJdt");
    }

    @RequestMapping(value = "searchLbjData", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public LbjDTO searchLbjData(String lbjid, Model model, HttpServletRequest request, HttpServletResponse response){
        LbjDTO lbjDTO = new LbjDTO();
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(lbjid);
        int wwcsl = 0;
        int sl = 0;
        if (scjhglLjgl.getWrksl()!=null&&!scjhglLjgl.getWrksl().equals("")){
            wwcsl = Integer.parseInt(scjhglLjgl.getWrksl());
        }
        if (scjhglLjgl.getSl()!=null&&!scjhglLjgl.getSl().equals("")){
            sl = Integer.parseInt(scjhglLjgl.getSl());
        }
        int wcsl = sl - wwcsl;
        lbjDTO.setWcsl(wcsl+"");
        lbjDTO.setWwcsl(wwcsl+"");
        lbjDTO.setLbjmc(scjhglLjgl.getLjmc());
        return lbjDTO;
    }
}
