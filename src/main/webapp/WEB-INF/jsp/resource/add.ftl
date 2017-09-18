<div class="box box-info">
    <div class="box-header with-border">
        <h3 class="box-title">新增资源</h3>
    </div>
    <!-- /.box-header -->
    <#import "spring.ftl" as spring />
    <!-- form start -->
    <form class="form-horizontal" role="form" method="post" modelAttribute="resource" id="updateForm">
        <div class="box-body">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">权限名称</label>
                <div class="input-group col-sm-8">
                <@spring.formInput "resource.name", "class='form-control' placeholder='请输入权限名称'"/>
                </div>
            </div>
            <div class="form-group">
                <label for="permission" class="col-sm-2 control-label">权限permission</label>
                <div class="input-group col-sm-8">
                <@spring.formInput "resource.permission", "class='form-control' placeholder='请输入权限permission'"/>
                </div>
            </div>
            <div class="form-group">
                <label for="url" class="col-sm-2 control-label">权限url</label>
                <div class="input-group col-sm-8">
                <@spring.formInput "resource.url", "class='form-control' placeholder='请输入权限权限url'"/>
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