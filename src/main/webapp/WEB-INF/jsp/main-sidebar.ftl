<!-- Left side column. contains the sidebar -->
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${ctx}/res/AdminLTE/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p><@shiro.principal property="username"/></p>
                <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
            </div>
        </div>
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu" data-widget="tree">
            <li class="header">MAIN NAVIGATION</li>
            <@shiro.hasRole name="admin">
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-share"></i> <span>权限管理</span><span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <#if menuUrl=="/admin/resource/list">
                        <li class="active"><a href="#"><i class="fa fa-circle-o"></i> 资源管理</a></li>
                    <#else>
                        <li><a href="${ctx}/admin/resource/list"><i class="fa fa-circle-o"></i> 资源管理</a></li>
                    </#if>
                    <#if menuUrl=="/admin/role/list">
                        <li class="active"><a href="#"><i class="fa fa-circle-o"></i> 角色管理</a></li>
                    <#else>
                        <li><a href="${ctx}/admin/role/list"><i class="fa fa-circle-o"></i> 角色管理</a></li>
                    </#if>
                    <#if menuUrl=="/admin/user/list">
                        <li class="active"><a href="#"><i class="fa fa-circle-o"></i> 用户管理</a></li>
                    <#else>
                        <li><a href="${ctx}/admin/user/list"><i class="fa fa-circle-o"></i> 用户管理</a></li>
                    </#if>
                </ul>
            </li>
            </@shiro.hasRole>
            <@shiro.hasAnyRoles name="admin,user">
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-share"></i> <span>支付</span><span class="pull-right-container"> <i class="fa fa-angle-left pull-right"></i></span>
                </a>
                <ul class="treeview-menu">
                    <li class="treeview">
                        <a href="#"><i class="fa fa-circle-o"></i> 支付帐户<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                            <#if menuUrl=="/alipayAccount/list">
                                <li class="active"><a href="#"><i class="fa fa-circle-o"></i> 支付宝</a></li>
                            <#else>
                                <li><a href="${ctx}/alipayAccount/list"><i class="fa fa-circle-o"></i> 支付宝</a></li>
                            </#if>
                            <#if menuUrl=="/wechatAccount/list">
                                <li class="active"><a href="#"><i class="fa fa-circle-o"></i> 微信</a></li>
                            <#else>
                                <li><a href="${ctx}/wechatAccount/list"><i class="fa fa-circle-o"></i> 微信</a></li>
                            </#if>
                        </ul>
                    </li>
                    <li class="treeview">
                        <a href="#"><i class="fa fa-circle-o"></i>企业信息<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span></a>
                        <ul class="treeview-menu">
                        <#if menuUrl=="/merchantInfo/list">
                            <li class="active"><a href="#"><i class="fa fa-circle-o"></i> 企业基本信息</a></li>
                        <#else>
                            <li><a href="${ctx}/merchantInfo/list"><i class="fa fa-circle-o"></i> 企业基本信息</a></li>
                        </#if>

                        <#if menuUrl=="/merchantContact/list">
                            <li class="active"><a href="#"><i class="fa fa-circle-o"></i> 企业联系人信息</a></li>
                        <#else>
                            <li><a href="${ctx}/merchantContact/list"><i class="fa fa-circle-o"></i> 企业联系人信息</a></li>
                        </#if>

                        <#if menuUrl=="/merchantManage/list">
                            <li class="active"><a href="#"><i class="fa fa-circle-o"></i> 企业经营信息</a></li>
                        <#else>
                            <li><a href="${ctx}/merchantManage/list"><i class="fa fa-circle-o"></i> 企业经营信息</a></li>
                        </#if>

                        <#if menuUrl=="/merchantFinance/list">
                            <li class="active"><a href="#"><i class="fa fa-circle-o"></i> 企业财务信息</a></li>
                        <#else>
                            <li><a href="${ctx}/merchantFinance/list"><i class="fa fa-circle-o"></i> 企业财务信息</a></li>
                        </#if>
                        </ul>
                    </li>
                </ul>
            </li>
            </@shiro.hasAnyRoles>
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>
