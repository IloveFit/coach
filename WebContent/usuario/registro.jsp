<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head> 
	<sx:head/>
	<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
	<title><s:text name="usuario.registro.titulo" /></title>
</head>
<body>
	<h3><s:text name="usuario.registro.bienvenido" /></h3>
	<s:form action="AltaUsuario">
		<s:textfield key="usuario.nombre" name="user.nombre" requiredLabel="true" ></s:textfield>
		<s:textfield key="usuario.apellido" name="user.apellido" requiredLabel="true"></s:textfield>
		<s:textfield key="usuario.dni" name="user.dni" requiredLabel="true"></s:textfield>
		<s:textfield key="usuario.email" name="user.email" requiredLabel="true" type="email"></s:textfield>
		<s:textfield key="usuario.password" name="user.password" type="password" requiredLabel="true"></s:textfield>
		<s:textfield key="usuario.pedido" name="user.pedido" tooltip="%{getText('usuario.pedido.descuento')}"></s:textfield>
		<s:submit value="Enviar"></s:submit>
	</s:form>
</body>
</html>