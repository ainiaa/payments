<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">角色列表</h3>

                <div class="box-tools">
                    <div class="input-group input-group-sm" style="width: 150px;">
                        <input type="text" name="table_search" class="form-control pull-right" placeholder="Search">

                        <div class="input-group-btn">
                            <button type="submit" class="btn btn-default"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                </div>
            </div>
        <#if showMsg??>
            <div id="msg_box">
                <#include msg_box>
            </div>
        </#if>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
                <table class="table table-hover">
                    <tbody>
                        <tr>
                            <th>#</th>
                            <th>角色标识</th>
                            <th>角色名称</th>
                            <th>角色字符串</th>
                            <th>操作</th>
                        </tr>
                    <#list list as role>
                    <tr>
                        <td>
                            <input type="checkbox" value="${role.id}" class="roleId"/>
                        </td>
                        <td>${role.id}</td>
                        <td>${role.name}</td>
                        <td>${role.sn}</td>
                        <td>
                            <a href="${ctx}/admin/role/update/${role.id}">更新</a>
                            <a href="${ctx}/admin/role/resources/${role.id}">设置资源</a>
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
                角色操作：
                <a class="btn btn-success" role="button" href="${pageContext.request.contextPath}/admin/role/add">添加角色</a>
                <a id="deleteRoleBtn" class="btn btn-success" role="button">删除角色</a>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>