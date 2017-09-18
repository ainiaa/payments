<div class="box box-info">
    <div class="box-header with-border">
        <h3 class="box-title">新增角色</h3>
    </div>
    <!-- /.box-header -->
<#import "spring.ftl" as spring />
    <!-- form start -->
    <form class="form-horizontal" role="form" method="post" modelAttribute="role" id="updateForm" enctype="multipart/form-data">
        <div class="box-body">
            <div class="form-group">
                <label for="name" class="col-sm-2 control-label">角色名称</label>
                <div class="input-group col-sm-8">
                <@spring.formInput "role.name", "class='form-control' placeholder='请输入角色名称'"/>
                </div>
            </div>
            <div class="form-group">
                <label for="sn" class="col-sm-2 control-label">角色表示字符串</label>
                <div class="input-group col-sm-8">
                <@spring.formInput "role.sn", "class='form-control' placeholder='请输入角色表示字符串'"/>
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