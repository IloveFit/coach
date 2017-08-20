<%@ taglib uri="/struts-tags" prefix="s"%>

<s:a action="inicio" namespace="/administrator/mensajes">
	<i class="fa fa-envelope-o"></i>
    <span class="label label-success"><s:property value="mensajesNoLeidos"/></span>
</s:a>