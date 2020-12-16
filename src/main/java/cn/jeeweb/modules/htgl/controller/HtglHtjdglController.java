package cn.jeeweb.modules.htgl.controller;

import cn.jeeweb.core.common.controller.BaseCRUDController;
import cn.jeeweb.core.security.shiro.authz.annotation.RequiresPathPermission;
import cn.jeeweb.modules.htgl.entity.HtglHtgl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${admin.url.prefix}/htgl/htjdgl")
@RequiresPathPermission("htgl:htjdgl")
public class HtglHtjdglController extends BaseCRUDController<HtglHtgl, String> {
}
