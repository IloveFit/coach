<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!-- user-information-area-start -->
<div class="user-information-area">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <div class="user-information">
                    <h2 class="fp-title"><s:text name="cliente.registro.titulo"></s:text></h2>
                    <div class="row">
                        <div class="col-md-8 col-md-offset-2">
                            <div class="user-information-content">
                            	<s:form action="registro" namespace="/cliente" theme="simple">
	                                <div class="uic-user-name">
	                                    <label><s:text name="form.nombre"></s:text><i class="fa fa-user"></i></label>
	                                    <s:textfield name="cliente.nombre" type="text" cssErrorClass="inputError" placeholder="%{getText('form.nombre')}"></s:textfield>
	                                    <s:fielderror fieldName="cliente.nombre" theme="ilovefit" class="fa fa-times-circle-o"/>
	                                </div>
	                                <div class="uic-user-name">
	                                    <label><s:text name="form.apellidos"></s:text><i class="fa fa-user"></i></label>
	                                    <s:textfield name="cliente.apellidos" type="text" cssErrorClass="inputError" placeholder="%{getText('form.apellidos')}" required="true"></s:textfield>
	                                </div>
	                                <div class="uic-email">
	                                    <label><s:text name="form.email"></s:text><i class="fa fa-envelope"></i></label>
	                                    <s:textfield name="cliente.email" type="email" cssErrorClass="inputError" placeholder="Email" required="true"></s:textfield>
	                                </div>
	                                <div class="uic-password">
	                                    <label><s:text name="form.password"></s:text><i class="fa fa-key"></i></label>
	                                    <s:textfield name="cliente.pass" type="password" cssErrorClass="inputError" placeholder="%{getText('form.password')}" required="true"></s:textfield>
	                                    <s:fielderror fieldName="cliente.pass" theme="ilovefit" class="fa fa-times-circle-o"/>
	                                </div>
	                                <div class="uic-password">
	                                    <label><s:text name="form.repetir.password"></s:text><i class="fa fa-key"></i></label>
	                                    <s:textfield name="repetirPass" type="password" cssErrorClass="inputError" placeholder="%{getText('form.repetir.password')}" required="true"></s:textfield>
	                                    <s:fielderror fieldName="repetirPass" theme="ilovefit" class="fa fa-times-circle-o"/>
	                                </div>
                                </s:form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- user-information-area-end -->