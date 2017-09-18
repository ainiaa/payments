<script type="text/javascript">
    $(function(){
        $("#deleteRoleBtn").on("click",function(){
            var checkedArray = [];
            $("input[class='roleId']:checked").each(function () {
                checkedArray.push($(this).val());
            });
            if(checkedArray.length == 0){
                alert("请至少选择一个角色");
            }
            $.post("${ctx}/admin/role/delete",{
                "roleIds":checkedArray
            },function (data) {
                if(data.success){
                    alert("删除用户成功!");
                    location.href="${ctx}/admin/role/list";

                }
            });
        });
    });
</script>