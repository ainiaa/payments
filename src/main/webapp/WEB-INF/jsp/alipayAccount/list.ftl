<div class="row">
    <div class="col-xs-12">
        <div class="box">
            <div class="box-header">
                <h3 class="box-title">支付宝帐户列表</h3>

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
                        <th>ID</th>
                        <th>备注</th>
                        <th>审核状态</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    <#list list as merchantAccount>
                    <tr>
                        <td>${merchantAccount.id}</td>
                        <td>${merchantAccount.remark}</td>
                        <td><#if merchantAccount.checkStatus =="1" >新增<#elseif merchantAccount.checkStatus == "2">拒绝<#elseif merchantAccount.checkStatus == "3">更新<#elseif merchantAccount.checkStatus == "4">通过<#else>-</#if></td>
                        <td><#if merchantAccount.status =="1" >新增<#elseif merchantAccount.status == "2">更新<#elseif merchantAccount.status == "3">完成<#else>-</#if></td>
                        <td>-/-</td>
                    </tr>
                    </#list>
                    </tbody></table>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
</div>