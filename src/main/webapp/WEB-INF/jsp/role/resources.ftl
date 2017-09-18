<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">角色资源列表</h3>

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
            <div>
                <span>${role.name}（${role.sn}）的拥有的权限有：</span>
            </div>
            <!-- /.box-header -->
            <div class="box-body table-responsive no-padding">
                <table class="table table-hover">
                    <tbody>
                    <tr>
                        <th>权限标识</th>
                        <th>权限名称</th>
                        <th>权限 url</th>
                        <th>资源权限字符串</th>
                        <th>操作</th>
                    </tr>
                    <#list list as resource>
                    <tr>
                        <td>${resource.id}</td>
                        <td>${resource.name}</td>
                        <td>${resource.url}</td>
                        <td>${resource.permission}</td>
                        <td>
                            <input type="checkbox" class="resourceId" value="${resource.id}">
                        </td>
                    </tr>
                    </#list>
                    <#list hasResourceList as hasResource>
                    <input type="hidden" class="hasResourceId" value="${hasResource.id}"/>
                    </#list>
                    </tbody>
                </table>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>