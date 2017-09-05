package com.a91coding.payments.controller;

import com.a91coding.payments.model.Role;
import com.a91coding.payments.model.User;
import com.a91coding.payments.service.IRoleService;
import com.a91coding.payments.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "/admin/user")
@RequiresPermissions("admin")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    @Qualifier("userService")
    private IUserService userService;

    @Autowired
    @Qualifier("roleService")
    private IRoleService roleService;

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request, Model model) {
        int userId = Integer.parseInt(request.getParameter("id"));
        User user = this.userService.selectByPrimaryKey(userId);
        model.addAttribute("user", user);
        return "showUser";
    }

    @RequestMapping("/all")
    public String listUser(Model model) {
        List<User> list = userService.listUser();
        model.addAttribute("users", list);
        return "listUser";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("users", userService.listUser());
        return "user/list";
    }

    /**
     * 跳转到添加用户的页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {
        logger.debug("跳转到添加用户的页面");
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.list());
        return "user/add";
    }

    /**
     * 添加用户保存的方法
     *
     * @param user
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user, HttpServletRequest request) {
        logger.debug("添加用户 post 方法");
        logger.debug(user.toString());
        List<Integer> roleIdList = new ArrayList<>();
        String[] roldIds = request.getParameterValues("roleId");
        for (String roleId : roldIds) {
            roleIdList.add(Integer.parseInt(roleId));
        }
        userService.insert(user, roleIdList);
        // 重定向到本 Controller 的 list 方法（Get 方式）
        return "redirect:list";
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public Map<String, Object> updateStatus(User userParam) {
        int userId = userParam.getId();
        int u = userService.updateUserStatusByPrimaryKey(userId, userParam.getStatus());
        Map<String, Object> result = new HashMap<>();
        if (u > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("errorInfo", "更新状态失败");
        }
        return result;
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
        User user = userService.selectByPrimaryKey(id);

        // 所有的角色列表
        List<Role> roles = roleService.list();
        model.addAttribute("roles", roleService.list());
        Map rolesMap = new HashMap();
        roles.forEach(role->{rolesMap.put(role.getId().toString(),role.getName());});
        model.addAttribute("rolesMap", rolesMap);
        /**
         * 根据用户 id 查询用户的所有角色
         */
        List<Role> hasRoles = userService.listUserRole(id);
        /**
         * 将用户的所有角色 id 添加到一个字符串中
         */
        List<Integer> hasRoleList = new ArrayList<>();
        hasRoles.forEach(r -> hasRoleList.add(r.getId()));
        // 指定用户拥有的角色信息

        Map userStatusMap = new HashMap();
        userStatusMap.put("0", "停用");
        userStatusMap.put("1", "启用");
        model.addAttribute("userStatusMap", userStatusMap);

        user.setHasRoleList(hasRoleList);
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * 更新用户的信息（包括更新用户绑定的角色）
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(User user, HttpServletRequest request) {
        // // TODO: 2016/9/18 这个过程还是可以优化的，如果属性没有发生变化的地方，是不须要更新的
        logger.debug("user => " + user);
        String[] roleIds = request.getParameterValues("roleId");
        List<Integer> roleIdList = new ArrayList<>();
        for (String roleId : roleIds) {
            roleIdList.add(Integer.valueOf(roleId));
        }
        userService.updateByPrimaryKey(user, roleIdList);
        return "redirect:/admin/user/list";
    }

    /**
     * 根据用户 id 跳转到用户权限的列表页面
     *
     * @return
     */
    @RequestMapping(value = "/resources/{id}", method = RequestMethod.GET)
    public String listResources(@PathVariable("id") Integer userId, Model model) {
        List<com.a91coding.payments.model.Resource> resourceList = userService.listAllResources(userId);
        User user = userService.selectByPrimaryKey(userId);
        model.addAttribute("resources", resourceList);
        model.addAttribute("user", user);
        return "user/resources";
    }

    /**
     * 批量删除用户
     * 1、删除用户数据
     * 2、删除用户绑定的角色数据
     *
     * @param userIds
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Map<String, Object> delete(
            @RequestParam("userIds[]") List<Integer> userIds) {
        Map<String, Object> result = new HashMap<>();
        try {
            userService.deleteUserAndRole(userIds);
            result.put("success", true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("errorInfo", e.getMessage());
        }
        return result;
    }
}