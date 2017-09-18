<ol class="breadcrumb">
<#if menuUrl=="/merchantInfo/list">
    <li><a href="${ctx}${menuUrl}"><i class="fa fa-dashboard"></i> 支付</a></li>
    <li><a href="${ctx}${menuUrl}">企业信息</a></li>
    <li <a href="${ctx}${menuUrl}">企业基本信息</a></li>
    <#if currUrl=="/merchantInfo/add">
        <li class="active">新增</li>
    <#elseif currUrl=="/merchantInfo/list">
        <li class="active">列表</li>
    <#else >
        <li class="active">${currUrl}</li>
    </#if>
<#elseif menuUrl=="/merchantContact/list">
    <li><a href="${ctx}${menuUrl}"><i class="fa fa-dashboard"></i> 支付</a></li>
    <li><a href="${ctx}${menuUrl}">企业信息</a></li>
    <li <a href="${ctx}${menuUrl}">企业联系人信息</a></li>
    <#if currUrl=="/merchantContact/add">
        <li class="active">新增</li>
    <#elseif currUrl=="/merchantContact/list">
        <li class="active">列表</li>
    <#else >
        <li class="active">${currUrl}</li>
    </#if>
<#elseif menuUrl=="/merchantFinance/list">
    <li><a href="${ctx}${menuUrl}"><i class="fa fa-dashboard"></i> 支付</a></li>
    <li><a href="${ctx}${menuUrl}">企业信息</a></li>
    <li <a href="${ctx}${menuUrl}">企业财务信息</a></li>
    <#if currUrl=="/merchantFinance/add">
        <li class="active">新增</li>
    <#elseif currUrl=="/merchantFinance/list">
        <li class="active">列表</li>
    <#else >
        <li class="active">${currUrl}</li>
    </#if>
<#elseif menuUrl=="/alipayAccount/list">
    <li><a href="${ctx}${menuUrl}"><i class="fa fa-dashboard"></i> 支付</a></li>
    <li><a href="${ctx}${menuUrl}">支付帐户</a></li>
    <li <a href="${ctx}${menuUrl}">支付宝</a></li>
    <#if currUrl=="/alipayAccount/add">
        <li class="active">新增</li>
    <#elseif currUrl=="/alipayAccount/list">
        <li class="active">列表</li>
    <#else >
        <li class="active">${currUrl}</li>
    </#if>
<#elseif menuUrl=="/wechatAccount/list">
    <li><a href="${ctx}${menuUrl}"><i class="fa fa-dashboard"></i> 支付</a></li>
    <li><a href="${ctx}${menuUrl}">支付帐户</a></li>
    <li <a href="${ctx}${menuUrl}">微信</a></li>
    <#if currUrl=="/wechatAccount/add">
        <li class="active">新增</li>
    <#elseif currUrl=="/wechatAccount/list">
        <li class="active">列表</li>
    <#else >
        <li class="active">${currUrl}</li>
    </#if>
<#elseif menuUrl=="/merchantManage/list">
    <li><a href="${ctx}${menuUrl}"><i class="fa fa-dashboard"></i> 支付</a></li>
    <li><a href="${ctx}${menuUrl}">企业信息</a></li>
    <li <a href="${ctx}${menuUrl}">企业经营信息</a></li>
    <#if currUrl=="/merchantManage/add">
        <li class="active">新增</li>
    <#elseif currUrl=="/merchantManage/list">
        <li class="active">列表</li>
    <#else >
        <li class="active">${currUrl}</li>
    </#if>
</#if>
</ol>