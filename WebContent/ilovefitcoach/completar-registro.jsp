<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<h1>COMPLETAR REGISTRO</h1>



<form action="<s:property value="messageOrderCESRequest.redsysUrl"/>" method="post">
    <input name="Ds_SignatureVersion" value="<s:property value="messageOrderCESRequest.ds_SignatureVersion"/>" type="text"/>
    <input name="Ds_MerchantParameters" value="<s:property value="messageOrderCESRequest.ds_MerchantParameters"/>" type="text"/>
    <input name="Ds_Signature" value="<s:property value="messageOrderCESRequest.ds_Signature"/>" type="text"/>
    <input type="submit" value="Comprar"/>
</form>