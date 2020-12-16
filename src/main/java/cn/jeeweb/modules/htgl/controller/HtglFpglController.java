package cn.jeeweb.modules.htgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.htgl.entity.HtglHtgl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${admin.url.prefix}/htgl/fpgl")
@RequiresPathPermission("htgl:fpgl")
public class HtglFpglController extends BaseCRUDController<HtglHtgl, String> {
}
