package com.a91coding.payments.controller;

import com.a91coding.payments.model.Resource;
import com.a91coding.payments.service.IResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/admin/resource")
@RequiresPermissions("admin")
@RequiresRoles("admin")
public class ResourceController extends BaseBackendController{
    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    @Qualifier("resourceService")
    private IResourceService resourceService;

    @RequestMapping("/show")
    public String show(HttpServletRequest request, Model model) {
        int resourceId = Integer.parseInt(request.getParameter("id"));
        Resource resource = resourceService.selectByPrimaryKey(resourceId);
        model.addAttribute("resource", resource);
        model.addAttribute("menuUrl", "/admin/resource/list");
        model.addAttribute("currUrl", "/admin/resource/show");
        return display(model, "resource/show");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", resourceService.listResource());
        model.addAttribute("menuUrl", "/admin/resource/list");
        model.addAttribute("currUrl", "/admin/resource/list");
        return display(model, "resource/list");
    }

    /**
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("resource", new Resource());
        model.addAttribute("menuUrl", "/admin/resource/list");
        model.addAttribute("currUrl", "/admin/resource/add");
        return display(model, "resource/add");
    }

    /**
     * 添加资源
     *
     * @param resource
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Resource resource, HttpServletRequest request) {
        resourceService.insert(resource);
        return "redirect:/admin/resource/list";
    }

    /**
     * 跳转到用户信息更新页面
     *
     * @param id    用户ID
     * @param model 请求类型
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model model) {
        // 要从数据库查询对象进行回显
        Resource resource = resourceService.selectByPrimaryKey(id);

        model.addAttribute("resource", resource);
        model.addAttribute("menuUrl", "/admin/resource/list");
        model.addAttribute("currUrl", "/admin/resource/update");
        return display(model, "resource/update");
    }

    /**
     * 更新资源
     *
     * @param resource
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(Resource resource, HttpServletRequest request) {
        logger.debug("resource => " + resource);
        resourceService.updateByPrimaryKey(resource);
        return "redirect:/admin/resource/list";
    }
}