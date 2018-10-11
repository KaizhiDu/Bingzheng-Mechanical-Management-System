package cn.jeeweb.modules.scjhgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.AjaxJson;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBjgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglBjzc;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
import cn.jeeweb.modules.scjhgl.service.IScjhglBjglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglBjzcService;
import cn.jeeweb.modules.scjhgl.service.IScjhglHtglService;
import cn.jeeweb.modules.scjhgl.service.IScjhglLjglService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Dscription: 生产计划管理 - 部件管理
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/8 13:06
 */
@Controller
@RequestMapping("${admin.url.prefix}/scjhgl/bjgl")
@RequiresPathPermission("scjhgl:bjgl")
public class ScjhglBjglController extends BaseCRUDController<ScjhglBjgl, String> {

    /**零件管理Service*/
    @Autowired
    private IScjhglBjglService scjhglBjglService;

    /**零件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**合同管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**部件组成Service*/
    @Autowired
    private IScjhglBjzcService scjhglBjzcService;

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

    /**
     * Dscription: 展示所有部件信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/8 17:04
     */
    @RequestMapping(value = "ajaxBjglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<ScjhglBjgl> ajaxBjglList(Queryable queryable, ScjhglBjgl scjhglBjgl, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<ScjhglBjgl> pageJson = scjhglBjglService.ajaxBjglList(queryable,scjhglBjgl);
        return pageJson;
    }
    
    /**
     * Dscription: 转到添加部件页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/9 9:33
     */
    @RequestMapping(value = "createBj", method={RequestMethod.GET, RequestMethod.POST})
    public String createBj(HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
        return display("createBj");
    }

    /**
     * Dscription: 保存部件基本信息
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/9 10:24
     */
    @RequestMapping(value = "saveBj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBj(ScjhglLjgl scjhglLjgl, HttpServletRequest request, HttpServletResponse response, Model model){
        scjhglLjgl.setWrksl(scjhglLjgl.getSl());
        scjhglLjgl.setSfsbj("1");
        scjhglLjgl.setSfwwcrk("0");
        scjhglLjglService.insert(scjhglLjgl);
    }

    /**
     * Dscription: 转到部件组成页面
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/9 10:32
     */
    @RequestMapping(value = "bjzc", method={RequestMethod.GET, RequestMethod.POST})
    public String bjzc(String id ,HttpServletRequest request, HttpServletResponse response, Model model){
        //得到计划ID
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(id);
        String jhid = scjhglLjgl.getHtid();
        //拿到所有该计划下的零件
        EntityWrapper<ScjhglLjgl> wrapper = new EntityWrapper<ScjhglLjgl>();
        wrapper.eq("HTID", jhid);
        wrapper.eq("SFSBJ", "0");
        List<ScjhglLjgl> ljList = scjhglLjglService.selectList(wrapper);
        model.addAttribute("ljList", ljList);
        //部件ID
        model.addAttribute("bjid", id);
        return display("bjzc");
    }

    /**
     * Dscription: 保存部件组成
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/9 12:04
     */
    @RequestMapping(value = "saveBjzc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void saveBjzc(String id, String check_val, HttpServletRequest request, HttpServletResponse response, Model model){
        String checkArray[] = check_val.split(",");
        String bjzc = "";
        for (int i=1;i<checkArray.length;i++){
           String ljmc = scjhglLjglService.selectById(checkArray[i]).getLjmc();
           if (bjzc.equals("")){
               bjzc = ljmc;
           }
           else{
               bjzc = bjzc + "," +ljmc;
           }
        }
        //得到部件信息，添加部件组成，最后更新
        ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(id);
        scjhglLjgl.setBjzc(bjzc);
        scjhglLjglService.updateById(scjhglLjgl);
    }

    /**
     * Dscription: 检查数量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/9 12:37
     */
    @RequestMapping(value = "checkSl", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int checkSl(String ljid, String bjid, HttpServletRequest request, HttpServletResponse response, Model model){
        int check = 0;
        AjaxJson ajaxJson = new AjaxJson();
        //得到零件的剩余数量
        String ljsyslS = scjhglLjglService.selectById(ljid).getSysl();
        //得到部件的数量
        String bjslS = scjhglLjglService.selectById(bjid).getSl();
        float ljsysl = 0;
        float bjsl = 0;
        if (ljsyslS!=null&&!ljsyslS.equals("")){
            ljsysl = Float.parseFloat(ljsyslS);
        }
        if (bjslS!=null&&!bjslS.equals("")){
            bjsl = Float.parseFloat(bjslS);
        }
        if (ljsysl<bjsl){
            check = 0;
        }
        else{
            check = 1;
        }
        return check;
    }

    /**
     * Dscription: 操作部件组成
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/9 13:47
     */
    @RequestMapping(value = "addDelete", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void addDelete(String flag, String ljid, String bjid, HttpServletRequest request, HttpServletResponse response, Model model){
        //添加
        if (flag.equals("add")){
            //零件减去部件数量
            //首先要拿到部件数量
            ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(bjid);
            float bjsl = 0;
            if (scjhglLjgl.getSl()!=null&&!scjhglLjgl.getSl().equals("")){
                bjsl = Float.parseFloat(scjhglLjgl.getSl());
            }

            ScjhglLjgl scjhglLjgl2 = scjhglLjglService.selectById(ljid);
            float ljsysl = 0;
            if (scjhglLjgl2.getSysl()!=null&&!scjhglLjgl2.getSysl().equals("")){
                ljsysl = Float.parseFloat(scjhglLjgl2.getSysl());
            }
            ljsysl = ljsysl - bjsl;
            scjhglLjgl2.setSysl(ljsysl+"");
            scjhglLjglService.updateById(scjhglLjgl2);

            //添加
            ScjhglBjzc scjhglBjzc = new ScjhglBjzc();
            scjhglBjzc.setBjid(bjid);
            scjhglBjzc.setLjid(ljid);
            scjhglBjzcService.insert(scjhglBjzc);
        }
        if (flag.equals("delete")){
            //零件加上部件数量
            ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(bjid);
            float bjsl = 0;
            if (scjhglLjgl.getSl()!=null&&!scjhglLjgl.getSl().equals("")){
                bjsl = Float.parseFloat(scjhglLjgl.getSl());
            }

            ScjhglLjgl scjhglLjgl2 = scjhglLjglService.selectById(ljid);
            float ljsysl = 0;
            if (scjhglLjgl2.getSysl()!=null&&!scjhglLjgl2.getSysl().equals("")){
                ljsysl = Float.parseFloat(scjhglLjgl2.getSysl());
            }
            ljsysl = ljsysl + bjsl;
            scjhglLjgl2.setSysl(ljsysl+"");
            scjhglLjglService.updateById(scjhglLjgl2);

            //删除
            EntityWrapper<ScjhglBjzc> wrapper = new EntityWrapper<ScjhglBjzc>();
            wrapper.eq("LJID", ljid);
            wrapper.eq("BJID", bjid);
            scjhglBjzcService.delete(wrapper);
        }
    }

    /**
     * Dscription: 查询部件组成
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/10 9:42
     */
    @RequestMapping(value = "serachBjzc", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<ScjhglBjzc> serachBjzc(String bjid, HttpServletRequest request, HttpServletResponse response, Model model){
        EntityWrapper<ScjhglBjzc> wrapper = new EntityWrapper<ScjhglBjzc>();
        wrapper.eq("BJID", bjid);
        List<ScjhglBjzc> scjhglBjzcs = scjhglBjzcService.selectList(wrapper);
        return scjhglBjzcs;
    }

    /**
     * Dscription: 删除部件，并且加上相应的零件剩余数量
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/10 9:43
     */
    @RequestMapping(value = "deleteBj", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public void addDelete(String ids, HttpServletRequest request, HttpServletResponse response, Model model){
        String idsArray[] = ids.split(",");
        for (int i=0;i<idsArray.length;i++){
            String bjid = idsArray[i];
            //得到部件数量
            String bjslS = scjhglLjglService.selectById(bjid).getSl();
            float bjsl = 0;
            if (bjslS!=null&&!bjslS.equals("")){
                bjsl = Float.parseFloat(bjslS);
            }
            EntityWrapper<ScjhglBjzc> wrapper = new EntityWrapper<ScjhglBjzc>();
            wrapper.eq("BJID", bjid);
            List<ScjhglBjzc> bjzcList = scjhglBjzcService.selectList(wrapper);
            for (ScjhglBjzc s: bjzcList) {
                String ljid = s.getLjid();
                ScjhglLjgl scjhglLjgl = scjhglLjglService.selectById(ljid);
                String ljsyslS = scjhglLjgl.getSysl();
                float ljsysl = 0;
                if (ljsyslS!=null&&!ljsyslS.equals("")){
                    ljsysl = Float.parseFloat(ljsyslS);
                }
                ljsysl = ljsysl + bjsl;
                scjhglLjgl.setSysl(ljsysl+"");
                scjhglLjglService.updateById(scjhglLjgl);
            }

            //删除所有相关的部件组成
            scjhglBjzcService.delete(wrapper);

            //删除部件
            scjhglLjglService.deleteById(bjid);
        }
    }
}
