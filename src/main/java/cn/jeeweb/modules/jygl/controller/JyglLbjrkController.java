package cn.jeeweb.modules.jygl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.model.PageJson;
import cn.jeeweb.core.query.data.Queryable;
import cn.jeeweb.core.query.wrapper.EntityWrapper;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.ckgl.entity.CkglBcp;
import cn.jeeweb.modules.ckgl.entity.CkglWwcgx;
import cn.jeeweb.modules.ckgl.entity.CkglWwcgydl;
import cn.jeeweb.modules.ckgl.service.ICkglBcpService;
import cn.jeeweb.modules.ckgl.service.ICkglWwcgxService;
import cn.jeeweb.modules.ckgl.service.ICkglWwcgydlSerivce;
import cn.jeeweb.modules.jygl.entity.JyglLbjrk;
import cn.jeeweb.modules.jygl.service.IJyglLbjrkService;
import cn.jeeweb.modules.scgl.entity.ScglGydlbz;
import cn.jeeweb.modules.scgl.entity.ScglLjgybz;
import cn.jeeweb.modules.scgl.service.IScglGydlbzService;
import cn.jeeweb.modules.scgl.service.IScglLjgybzService;
import cn.jeeweb.modules.scjhgl.entity.ScjhglHtgl;
import cn.jeeweb.modules.scjhgl.entity.ScjhglLjgl;
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
 * Dscription: 检验管理 - 零部件入库
 * @author : Kevin Du
 * @version : 1.0
 * @date : 2018/10/10 16:34
 */
@Controller
@RequestMapping("${admin.url.prefix}/jygl/lbjrk")
@RequiresPathPermission("jygl:lbjrk")
public class JyglLbjrkController extends BaseCRUDController<JyglLbjrk, String> {

    /**检验管理 - 零部件入库管理*/
    @Autowired
    private IJyglLbjrkService jyglLbjrkService;

    /**生产管理-零部件工艺编制Service*/
    @Autowired
    private IScglLjgybzService scglLjgybzService;

    /**生产计划管理-零部件管理Service*/
    @Autowired
    private IScjhglLjglService scjhglLjglService;

    /**生产计划管理-计划管理Service*/
    @Autowired
    private IScjhglHtglService scjhglHtglService;

    /**仓库管理-半成品管理Service*/
    @Autowired
    private ICkglBcpService ckglBcpService;

    /**仓库管理-未完成工序Service*/
    @Autowired
    private ICkglWwcgxService ckglWwcgxService;

    /**仓库管理-未完成工艺大类Service*/
    @Autowired
    private ICkglWwcgydlSerivce ckglWwcgydlSerivce;

    /**生产管理-工艺大类编制Service*/
    @Autowired
    private IScglGydlbzService scglGydlbzService;




    /**
     * @Description:    搜索项
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 17:22
     * @Version:        1.0
     */
    @Override
    public void preList(Model model, HttpServletRequest request, HttpServletResponse response){
        EntityWrapper<ScjhglHtgl> wrapper = new EntityWrapper<ScjhglHtgl>();
        wrapper.orderBy("rq", false);
        wrapper.eq("SFWC","0");
        List<ScjhglHtgl> list = scjhglHtglService.selectList(wrapper);
        model.addAttribute("htList", list);
    }

    /**
     * @Description:    展示所有零部件信息（除了未完成入库的零部件）
     * @Author:         杜凯之
     * @CreateDate:     2018/9/12 16:53
     * @Version:        1.0
     */
    @RequestMapping(value = "ajaxlbjglList", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageJson<JyglLbjrk> ajaxlbjglList(Queryable queryable, JyglLbjrk jyglLbjrk, HttpServletRequest request, HttpServletResponse response, Model model){
        PageJson<JyglLbjrk> pageJson = jyglLbjrkService.ajaxlbjglList(queryable,jyglLbjrk);
        return pageJson;
    }

    /**
     * Dscription: 入半成品库
     * @author : Kevin Du
     * @version : 1.0
     * @date : 2018/10/11 12:02
     */
    @RequestMapping(value = "rbcpk", method={RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public int rbcpk(String ljid, HttpServletRequest request, HttpServletResponse response, Model model){
        //0 没有未完成数据  1 有未完成数据  2 没有编辑工艺信息
        int sfwwc = 0;
        //先得到零件信息
        ScjhglLjgl ljgl = scjhglLjglService.selectById(ljid);
        //然后得到零件下属工艺大类信息
        EntityWrapper<ScglGydlbz> wrapper = new EntityWrapper<ScglGydlbz>();
        wrapper.eq("LJID", ljid);
        List<ScglGydlbz> scglGydlbzs = scglGydlbzService.selectList(wrapper);
        //再得到零件下属工艺信息
        List<ScglLjgybz> ljgybzByLjid = scglLjgybzService.getLjgybzByLjid(ljid);
        if (ljgybzByLjid.size()==0){
            sfwwc = 2;
        }
        else{
            for (ScglLjgybz s : ljgybzByLjid) {
                if (s.getJhscsl()!=0){
                    sfwwc = 1;
                    break;
                }
            }
        }
        //如果没有未完成数据的话，进行后续操作
        if (sfwwc==0){
            int ljsl = 0;
            if (ljgl.getSl()!=null&&!ljgl.getSl().equals("")){
                ljsl = Integer.parseInt(ljgl.getSl());
            }
            //然后得到新入半成品的零件数量
            int bcpwwcsl = 0;
            for (ScglLjgybz s : ljgybzByLjid) {
                int sysl = s.getSysl();
                int ss = sysl;
                if (ss>bcpwwcsl){
                    bcpwwcsl = ss;
                }
            }
            //下面要把零件，工艺大类编制，工艺小类编制的信息 复制一份给bcp，wwcgydl，wwcgx
            //1. 半成品
            CkglBcp c = new CkglBcp();
            c.setLbjid(ljid+"-dd");
            c.setLbjth(ljgl.getLjth());
            c.setLbjmc(ljgl.getLjmc());
            c.setRksl(bcpwwcsl+"");
            c.setSfswwcbcp("1");
            ckglBcpService.insert(c);

            //2. 然后复制一份到wwcgydl里面
            for (ScglGydlbz s : scglGydlbzs) {
                CkglWwcgydl c2 = new CkglWwcgydl();
                c2.setId(s.getId()+"-dd");
                c2.setLjid(ljid+"-dd");
                c2.setGydlid(s.getGydlid());
                c2.setGydlmc(s.getGydlmc());
                c2.setPx(s.getPx());
                ckglWwcgydlSerivce.insert(c2);
            }

            // 3.再复制一份到wwcgx里面
            for (ScglLjgybz s : ljgybzByLjid) {
                CkglWwcgx c3 = new CkglWwcgx();
                c3.setId(s.getId()+"-dd");
                c3.setGydlbzid(s.getGydlbzid()+"-dd");
                c3.setGyxlid(s.getGyxlid());
                c3.setGyxlmc(s.getGyxlmc());
                c3.setMs(s.getMs());
                c3.setPx(s.getPx());
                c3.setScsfxs(s.getScsfxs());
                c3.setJhscsl(s.getJhscsl());
                c3.setSl(bcpwwcsl);
                int sysl = s.getSysl();
                //int ss = ljsl - sysl;
                c3.setWrksl(bcpwwcsl);
                c3.setSysl(sysl);
                ckglWwcgxService.insert(c3);
            }

//            //最后需要删除原零件，下属工艺大类编制，和下属国内工艺小类编制
//            scjhglLjglService.deleteById(ljid);
//            scglGydlbzService.delete(wrapper);
//            for (ScglLjgybz s : ljgybzByLjid) {
//                scglLjgybzService.deleteById(s.getId());
//            }
        }
        return sfwwc;
    }
}
