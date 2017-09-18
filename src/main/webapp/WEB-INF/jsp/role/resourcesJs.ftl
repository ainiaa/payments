<script type="text/javascript">
    $(function(){
        // 为角色所绑定的资源进行显示设置 begin
        var resourceArray = new Array();
        $(".hasResourceId").each(function(){
            var hasResource = $(this);
            resourceArray.push(hasResource.val());
        });

        $(".resourceId").each(function(){
            var resource = $(this);
            if($.inArray(resource.val(),resourceArray) >= 0){
                resource.attr("checked","checked");
            }
        });
        // 为角色所绑定的资源进行显示设置 end

        // 为复选框设置单击事件
        $(".resourceId").on("click",function(){
            var c = 0;
            var ifChecked = this.checked
            if(ifChecked){
                c = 1;
            }
            var roleId = "${role.id}";
            var resourceId = $(this).val();
            $.post("${ctx}/admin/role/resource",{
                "roleId":roleId,
                "resourceId":resourceId,
                "check":c
            },function(data){
                if(data.success){
                    var ctext = c==0 ? "权限取消成功":"权限绑定成功";
                    alert(ctext);
                }else{
                    alert(data.errorInfo);
                }
            });
        });
    })
</script>