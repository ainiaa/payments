package com.a91coding.payments.controller;

import com.a91coding.payments.model.Role;
import com.a91coding.payments.model.User;
import com.a91coding.payments.service.IResourceService;
import com.a91coding.payments.service.IRoleService;
import com.a91coding.payments.service.IUserService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/role")
@RequiresPermissions("admin")
@RequiresRoles("admin")
public class RoleController extends BaseBackendController{
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Autowired
    @Qualifier("roleService")
    private IRoleService roleService;

    @Autowired
    @Qualifier("resourceService")
    private IResourceService resourceService;

    @RequestMapping("/show")
    public String show(HttpServletRequest request, Model model) {
        int roleId = Integer.parseInt(request.getParameter("id"));
        Role role = roleService.selectByPrimaryKey(roleId);
        model.addAttribute("role", role);
        model.addAttribute("menuUrl", "/admin/role/list");
        model.addAttribute("currUrl", "/admin/role/show");
        return display(model, "role/show");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", roleService.list());
        model.addAttribute("extJsFtl","role/listExtJs.ftl");
        model.addAttribute("menuUrl", "/admin/role/list");
        model.addAttribute("currUrl", "/admin/role/list");
        return display(model, "role/list");
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("role", new Role());
        model.addAttribute("menuUrl", "/admin/role/list");
        model.addAttribute("currUrl", "/admin/role/add");
        return display(model, "role/add");
    }

    /**
     * 添加用户保存的方法
     *
     * @param role
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Role role, HttpServletRequest request) {
        logger.debug("role:" + role.toString());
        roleService.insert(role);
        return "redirect:/admin/role/list";
    }

    /**
     * @param id    用户ID
     * @param model 请求类型
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer id, Model model) {
        Role role = roleService.selectByPrimaryKey(id);
        model.addAttribute("role", role);
        return display(model, "role/update");
    }

    /**
     * @param role
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(Role role, HttpServletRequest request) {
        logger.debug("role => " + role);
        roleService.updateByPrimaryKey(role);
        return "redirect:/admin/role/list";
    }
    @RequestMapping(value = "/resources/{id}", method = RequestMethod.GET)
    public String resources(Model model, @PathVariable("id") Integer id) {
        Role role = roleService.selectByPrimaryKey(id);
        List<com.a91coding.payments.model.Resource> hasResourceList = roleService.listRoleResource(id);
        model.addAttribute("role", role);
        model.addAttribute("list", resourceService.listResource());
        model.addAttribute("hasResourceList", hasResourceList);
        model.addAttribute("extJsFtl","role/resourcesJs.ftl");
        model.addAttribute("menuUrl", "/admin/role/list");
        model.addAttribute("currUrl", "/admin/role/resources");

        return display(model, "role/resources");
    }

    @ResponseBody
    @RequestMapping(value = "/resource", method = RequestMethod.POST)
    public Map<String, Object> resource(Model model, @RequestParam("roleId") Integer roleId,@RequestParam("resourceId") Integer resourceId,@RequestParam("check") Integer check) {
        Map<String, Object> result = new HashMap<>();
        int status;
        if (check == 0) {
            status = roleService.deleteRoleResource(roleId, resourceId);
        } else {
            status = roleService.addRoleResource(roleId, resourceId);
        }
        if (status > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("errorInfo", "更新状态失败");
        }
        return result;
    }

}