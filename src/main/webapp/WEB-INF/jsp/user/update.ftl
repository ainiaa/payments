<#setting classic_compatible=true>
<#assign ctx=request.getContextPath()>
<html>
    <head>
        <title>修改用户信息</title>
    </head>
    <body>
        <div class="container">
        <#include "common.ftl"/>
            <span></span>
            <div class="panel panel-info">
                <div class="panel-heading">修改用户信息</div>
                <div class="panel-body">
                <#import "spring.ftl" as spring />
                    <form method="post" modelAttribute="user" id="updateForm" class="form-horizontal" role="form">

                        <div class="form-group">
                            <label for="inputUsername" class="col-sm-2 control-label">用户名</label>
                            <div class="col-sm-10">
                                <@spring.formInput "user.username", "class='col-sm-2' placeholder='请输入用户名'"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputNickname" class="col-sm-2 control-label">昵称</label>
                            <div class="col-sm-10">
                                <@spring.formInput "user.nickname","class='col-sm-2' placeholder='昵称'"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword" class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input class="form-control" id="inputPassword" value="${user.password}" readonly/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputStatus" class="col-sm-2 control-label">状态</label>
                            <div class="col-sm-10">
                                <@spring.formSingleSelect "user.status", userStatusMap />
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">角色</label>
                            <div class="col-sm-10">
                            <@spring.formCheckboxes "user.hasRoleList", rolesMap, "<br>"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <input class="btn btn-default" role="button" type="submit" value="提交修改">
                                <input class="btn btn-default" role="button" type="reset" value="重置">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="${ctx}/res/jquery-3.1.0.min.js"></script>
    </body>
</html>
