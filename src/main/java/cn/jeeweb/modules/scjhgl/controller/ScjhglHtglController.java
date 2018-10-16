package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
* @Description:    生产计划管理-合同管理
* @Author:         杜凯之
* @CreateDate:     2018/9/12 14:22
* @Version:        1.0
*/
@Controller
@RequestMapping("${admin.url.prefix}/scjhgl/htgl")
@RequiresPathPermission("scjhgl:htgl")
public class ScjhglHtglController extends BaseCRUDController<ScjhglHtgl, String> {

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**零件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**零件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;

    /**
    * @Description:    展示所有合同信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 15:42
    * @Version:        1.0
    */
    @RequestMapping(value = "createHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String creatHt(HttpServletRequest request, HttpServletResponse response, Model model){
        return display("create");
    }

    /**
    * @Description:    添加合同
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 15:43saveHt
    * @Version:        1.0
    */
    @RequestMapping(value = "saveHt",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveHt(HttpServletRequest request, HttpServletResponse response, Model model, ScjhglHtgl scjhglHtgl){
        if (scjhglHtgl.getId().equals("")){
            scjhglHtgl.setId(null);
        }
        if (scjhglHtgl.getId()==null){
            scjhglHtglService.insert(scjhglHtgl);
        }
        else{
            scjhglHtglService.updateById(scjhglHtgl);
        }
    }

    /**
    * @Description:    更新合同信息
    * @Author:         杜凯之
    * @CreateDate:     2018/9/12 16:04
    * @Version:        1.0
    */
    @RequestMapping(value = "updateHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String updateHt(String id ,HttpServletResponse response, HttpServletRequest request, Model model){
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(id);
        model.addAttribute("scjhglHtgl", scjhglHtgl);
        return display("update");
    }

    /**
    * @Description:    删除
    * @Author:         杜凯之
    * @CreateDate:     2018/9/13 14:03
    * @Version:        1.0
    */
    @RequestMapping(value = "deleteHt",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public AjaxJson deleteHt(String id , HttpServletRequest request, HttpServletResponse response, Model model){
        AjaxJson ajaxJson = new AjaxJson();
            //计划ID
            String jhid = id;
            //首先要判断该计划ID下的所有工艺是否还有剩余
            Boolean flag = false;
            List<ScglLjgybz> ljgybzByJhid = scglLjgybzService.getLjgybzByJhid(jhid);
            if (ljgybzByJhid!=null){
                for (ScglLjgybz s: ljgybzByJhid) {
                    int sysl = s.getSysl();
                    if (sysl>0){
                        flag = true;
                        break;
                    }
                }
            }
            //如果flag为假，就可以删除
            if (!flag){
                //先删除零件工艺编制信息
                if (ljgybzByJhid!=null){
                    for (ScglLjgybz s: ljgybzByJhid) {
                        scglLjgybzService.deleteById(s.getId());
                    }
                }

                //然后删除工艺大类信息
                List<ScglGydlbz> gydlbzByjhid = scglGydlbzService.getGydlbzByjhid(jhid);
                if (gydlbzByjhid!=null){
                    for (ScglGydlbz s: gydlbzByjhid) {
                        scglGydlbzService.deleteById(s.getId());
                    }
                }

                //再删除零件信息
                EntityWrapper<ScjhglLjgl> wrapper = new EntityWrapper<ScjhglLjgl>();
                wrapper.eq("HTID",jhid);
                List<ScjhglLjgl> scjhglLjgls = scjhglLjglService.selectList(wrapper);
                if (scjhglLjgls!=null){
                    scjhglLjglService.delete(wrapper);
                }

                //最后删除计划信息
                scjhglHtglService.deleteById(jhid);
                ajaxJson.setMsg("删除成功");
            }
            else{
                ajaxJson.setMsg("该计划未完成无法删除");
            }
        return ajaxJson;
    }

    /**
     * Dscription: 转到复制计划页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 11:49
     */
    @RequestMapping(value = "copyHt",method = {RequestMethod.GET,RequestMethod.POST})
    public String copyHt(String id, HttpServletRequest request, HttpServletResponse response, Model model){
        ScjhglHtgl scjhglHtgl = scjhglHtglService.selectById(id);
        model.addAttribute("scjhglHtgl",scjhglHtgl);
        return display("copy");
    }

    /**
     * Dscription: 创建复制内容
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/16 11:55
     */
    @RequestMapping(value = "saveCopy",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void saveCopy(ScjhglHtgl scjhglHtgl, HttpServletRequest request, HttpServletResponse response, Model model){
        //得到原计划ID
        String yjhid = scjhglHtgl.getId();
        //先拿到零件工艺信息，进行复制
        List<ScglLjgybz> ljgybzByJhidList = scglLjgybzService.getLjgybzByJhid(yjhid);
        for (ScglLjgybz s: ljgybzByJhidList) {
            ScglLjgybz ss = new ScglLjgybz();
            ss.setGydlbzid("gydlbzid");
            ss.setGyxlid(s.getGyxlid());
            ss.setGyxlmc(s.getGyxlmc());
            ss.setMs(s.getMs());
            ss.setPx(s.getPx());
            ss.setSl(s.getSl());
            ss.setWrksl(s.getSl());
            ss.setSysl(s.getSysl());
            ss.setScsfxs("1");
            scglLjgybzService.insert(ss);
        }
        //然后得到所有被复制的工艺大类编制信息，进行复制
        List<ScglGydlbz> gydlbzByjhidList = scglGydlbzService.getGydlbzByjhid(yjhid);
        for (ScglGydlbz s: gydlbzByjhidList) {
            //新的工艺大类编制ID
            String xgydlid  = UUID.randomUUID().toString().replaceAll("-","");
            //拿到原工艺大类ID
            String ygydlid = s.getId();
            //找到原工艺大类ID下属所有的工艺信息
            List<ScglLjgybz> ljgybzByJhidGydlidList = scglLjgybzService.getLjgybzByJhidGydlid(yjhid, ygydlid);
            for (ScglLjgybz l: ljgybzByJhidGydlidList) {
                //找到新添加的零件工艺信息，把工艺大类编制id，更新进去
                EntityWrapper<ScglLjgybz> wrapper = new EntityWrapper<ScglLjgybz>();
               //暂时无法进行，待续
            }
        }
    }
}
