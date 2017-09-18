<script type="text/javascript">
    $(function(){
        $(".status").each(function(){
            var status = $(this).attr("data-status");
            var oper = $(this);
            if(status == 1){
                oper.text("禁用");
            }else {
                oper.text("启用");
            }
        });

        $(".status").on("click",function(){
            var oper = $(this);
            var user_id = oper.attr("data-id");
            var user_status = oper.attr("data-status");
            var update_status = (user_status == 1 ? 0: 1);
            $.post("${ctx}/admin/user/updateStatus",
                    {
                        id:user_id,
                        status:update_status
                    },function(data){
                        if(data.success){
                            alert("修改状态成功！");
                            var oper_name = (update_status == 1 ? "禁用":"启用");
                            oper.attr("data-status",update_status);
                            oper.text(oper_name);
                        }else{
                            alert("失败");
                        }
                    }
            );

        });


        // 批量删除
        $("#deleteUserBtn").on("click",function(){
            var checkedArray =[];
            $('input[class="userId"]:checked').each(function(){
                checkedArray.push($(this).val());
            });

            if(checkedArray.length==0){
                alert("您还没有选择要删除的内容!");
                return;
            }
            // 这里也可以使用表单提交的方式删除
            $.ajax({
                type:"post",
                url:"${ctx}/admin/user/delete",
                dataType:"json",
                data:{
                    userIds:checkedArray,
                    testData:"testStr"
                },
                success:function (data) {
                    if(data.success){
                        alert("数据删除成功!");
                        location.href = "${ctx}/admin/user/list";
                    }else {
                        alert(data.errorInfo);
                    }
                },
                error:function () {
                    alert("您没有相应的权限删除用户数据,请联系管理员");
                }

            });
        });
    })
</script>