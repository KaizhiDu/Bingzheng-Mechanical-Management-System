package cn.jeeweb.modules.sjfx.controller;
import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import cn.jeeweb.modules.sjfx.dto.JhjdtDTO;
import cn.jeeweb.modules.sjfx.dto.ZjhDTO;
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
 * Dscription: 数据分析 - 计划进度图
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/24 9:34
 */
@Controller
@RequestMapping("${admin.url.prefix}/sjfx/jhjdt")
@RequiresPathPermission("sjfx:jhjdt")
public class SjfxJhjdtController extends BaseCRUDController<ScjhglHtgl, String> {

    /**计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**零部件管理Service*/
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
        ScjhglHtgl firstJh = list.get(0);
        model.addAttribute("firstJh" ,firstJh);
        model.addAttribute("htList", list);
    }

    /**
     * Dscription: 得到计划下所有零部件的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/24 13:55
     */
    @RequestMapping(value = "searchData",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public List<JhjdtDTO> searchData(String jhid, Model model, HttpServletRequest request, HttpServletResponse response){
        //用计划ID得到下属所有零部件信息
        List<ScjhglLjgl> ljByjhid = scjhglLjglService.getLjByjhid(jhid);
        List<JhjdtDTO> dataList = new ArrayList<JhjdtDTO>();
        for (ScjhglLjgl scjhglLjgl : ljByjhid) {
            JhjdtDTO jhjdtDTO = new JhjdtDTO();
            jhjdtDTO.setLbjmc(scjhglLjgl.getLjmc());
            jhjdtDTO.setLbjth(scjhglLjgl.getLjth());
            String wwcsl = scjhglLjgl.getWrksl();
            String zsl = scjhglLjgl.getSl();
            int zsli = 0;
            int wwcsli = 0;
            if (wwcsl!=null&&!wwcsl.equals("")){
                wwcsli = Integer.parseInt(wwcsl);
            }
            if (zsl!=null&&!zsl.equals("")){
                zsli = Integer.parseInt(zsl);
            }
            int wcsli = zsli - wwcsli;
            String wcsl = wcsli+"";
            jhjdtDTO.setWcsl(wcsl);
            jhjdtDTO.setWwcsl(wwcsl);
            dataList.add(jhjdtDTO);
        }
        return dataList;
    }

    /**
     * Dscription: 转到查看总计划页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/24 17:27
     */
    @RequestMapping(value = "checkZjh",method = {RequestMethod.GET,RequestMethod.POST})
    public String checkZjh(String jhid, Model model, HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("jhid", jhid);
        return display("checkZjh");
    }

    /**
     * Dscription: 得到总计划的信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/24 18:03
     */
    @RequestMapping(value = "searchZjhData",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ZjhDTO searchZjhData(String jhid, Model model, HttpServletRequest request, HttpServletResponse response){
        //用计划ID得到下属所有零部件信息
        List<ScjhglLjgl> ljByjhid = scjhglLjglService.getLjByjhid(jhid);
        ZjhDTO zjhDTO = new ZjhDTO();
        int zwcsl = 0;
        int zwwcsl = 0;
        for (ScjhglLjgl s : ljByjhid) {
            int sl = 0;
            int wwcsl = 0;
            if (s.getSl()!=null&&!s.getSl().equals("")){
                sl = Integer.parseInt(s.getSl());
            }
            if (s.getWrksl()!=null&&!s.getWrksl().equals("")){
                wwcsl = Integer.parseInt(s.getWrksl());
            }
            int wcsl = sl - wwcsl;
            zwcsl = zwcsl + wcsl;
            zwwcsl = zwwcsl + wwcsl;
        }
        String jhhb = scjhglHtglService.selectById(jhid).getHtbh();
        zjhDTO.setWcsl(zwcsl+"");
        zjhDTO.setWwcsl(zwwcsl+"");
        zjhDTO.setJhbh(jhhb);

        return zjhDTO;
    }
}
