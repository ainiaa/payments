<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">商户联系人信息列表</h3>

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
                    <tbody><tr>
                        <th></th>
                        <th>用户标识</th>
                        <th>用户名</th>
                        <th>用户昵称</th>
                        <th>用户状态</th>
                        <th>用户操作</th>
                    </tr>
                    <#list users as user>
                    <tr>
                        <td><input type="checkbox" class="userId" value="${user.id}"></td>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.nickname}</td>
                        <td >
                            【<a href="#" class="status" data-id="${user.id}" data-status="${user.status}">启用</a>】
                        </td>
                        <td>
                            <a href="${ctx}/admin/user/update/${user.id}">更新</a>
                            <a href="${ctx}/admin/user/resources/${user.id}">查询权限</a>
                        </td>
                    </tr>
                    </#list>
                    </tbody></table>
                用户操作：
                <a class="btn btn-primary" role="button" href="${ctx}/admin/user/add">添加用户</a>
                <a class="btn btn-primary" role="button" href="#" id="deleteUserBtn" >删除用户</a>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>