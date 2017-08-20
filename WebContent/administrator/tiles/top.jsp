<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div class="row">
	<s:url var="topUrl" value="/administrator/coach/top.action"/>
   	<sj:div href="%{topUrl}" updateFreq="120000" value="" resizable="false" droppable="false" draggable="false" selectable="false" sortable="false" >
	</sj:div>
</div>