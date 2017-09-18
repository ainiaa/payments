<div class="box box-info">
    <div class="box-header with-border">
        <h3 class="box-title">新增支付宝帐户</h3>
    </div>
    <!-- /.box-header -->
    <#import "spring.ftl" as spring />
    <!-- form start -->
    <form class="form-horizontal" role="form" method="post" modelAttribute="merchantAccount" id="updateForm" enctype="multipart/form-data">
        <div class="box-body">
            <div class="form-group">
                <label for="remark" class="col-sm-3 control-label">备注</label>
                <div class="input-group col-sm-7">
                    <@spring.formInput "merchantAccount.remark", "class='form-control' placeholder='请输入备注'"/>
                </div>
            </div>
            <div class="form-group">
                <label for="infoId" class="col-sm-3 control-label">企业基本信息</label>
                <div class="input-group col-sm-7">
                <@spring.formSingleSelect "merchantAccount.infoId", infoIdMap, "class='form-control'"/>
                </div>
            </div>
            <div class="form-group">
                <label for="contactId" class="col-sm-3 control-label">企业联系人信息</label>
                <div class="input-group col-sm-7">
                    <@spring.formSingleSelect "merchantAccount.contactId", contactMap, "class='form-control'"/>
                </div>
            </div>
            <div class="form-group">
                <label for="manageId" class="col-sm-3 control-label">企业经营信息</label>
                <div class="input-group col-sm-7">
                <@spring.formSingleSelect "merchantAccount.manageId", manageMap, "class='form-control'"/>
                </div>
            </div>
            <div class="form-group">
                <label for="financeId" class="col-sm-3 control-label">企业财务信息</label>
                <div class="input-group col-sm-7">
                    <@spring.formSingleSelect "merchantAccount.financeId", financeIdMap, "class='form-control'"/>
                </div>
            </div>
        </div>
        <!-- /.box-body -->
        <div class="box-footer">
            <button type="submit" class="btn btn-default">取消</button>
            <button type="submit" class="btn btn-primary">提交</button>
        </div>
        <!-- /.box-footer -->
    </form>
</div>