
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
            <div>
                用户名：${user.username}，昵称：${user.nickname}
            </div>
            <div class="box-body table-responsive no-padding">
                <table class="table table-hover">
                    <tbody><tr>
                        <th>资源名称</th>
                        <th>资源 url</th>
                        <th>资源权限字符串</th>
                    </tr>
                    <#list resources as resource>
                    <tr>
                        <td>${resource.name}</td>
                        <td>${resource.url}</td>
                        <td>${resource.permission}</td>
                    </tr>
                    </#list>
                    </tbody></table>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>