<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="col-md-6 col-sm-6 col-xs-12">
	<div class="header-top-right">
		<div class="single-htr">
	        <i class="fa fa-shopping-basket"></i>
	        <span>0 iteam - 00$</span>
	    </div>
	    <div class="single-htr">
	        <i class="fa fa-key"></i>
	        <span id="sign_in">Sign in</span>
	        <div id="sign_in_form" class="signin-form-area">
	            <div class="signin-form-area-top">
	                <h3>Fitness <span class="h-light">Trainer</span></h3>
	                <h6>BEST FITNESS CLUB</h6>
	                <div class="signin-form">
	                    <form action="#">
	                        <div class="signin-inputs">
	                            <div class="input-email">
	                                <input class="femail" type="text" placeholder="Email" required="">
	                            </div>
	                            <div class="input-password">
	                                <input class="fpass type_password" type="password" placeholder="Password" required="">
	                                <span class="show-pass">Show</span>
	                            </div>
	                        </div>
	                        <div class="remember-recover clearfix">
	                            <div class="remember">
	                                <input type="checkbox">
	                                <span>Remember me</span>
	                            </div>
	                            <div class="recover">
	                                <a href="#">Recover password</a>
	                            </div>
	                        </div>
	                        <button class="sign-in-btn hover-bs">SIGN IN</button>
	                        <div class="signin-or">
	                            <span>OR</span>
	                        </div>
	                    </form>
	                    <button class="reg-by-fb-btn hover-bs">REGISTER WITH FACEBOOK</button>
	                </div>
	            </div>
	            <div class="reg-account">
	                <p>Don’t have an account?<a href="#">Register</a></p>
	            </div>
	        </div>
	    </div>
	    <div class="single-htr">
	        <i class="fa fa-user-times"></i>
	        <span id="top_reg"><s:text name="cliente.registro"></s:text></span>
	        <div class="register-form-area" id="top_reg_form">
	            <div class="rf-head">
	                <span class="rfa-close" id="rfa_close"><i class="fa fa-times"></i></span>
	                <img src="imagenes/ilovefitcoach/logo-reg.png" alt="">
	                <p><s:text name="cliente.registro.texto"></s:text></p>
	            </div>
	            <div class="rf-body clearfix">
	                <div class="rf-body-or-area">
	                    <span>or</span>
	                </div>
	                <div class="rf-body-left">
	                    <s:form action="registro" namespace="/cliente">
	                        <div class="rf-inputs-area">
	                            <div class="rf-input-email rf-inputs">
	                            	<s:textfield name="cliente.email" class="rf-email" type="email" placeholder="Email" required="true"></s:textfield>
	                            </div>
	                            <div class="rf-input-name rf-inputs">
	                            	<s:textfield name="cliente.nombre" class="rf-name" type="text" placeholder="%{getText('form.nombre')}" required="true"></s:textfield>
	                            </div>
	                            <div class="rf-input-name rf-inputs">
	                            	<s:textfield name="cliente.apellidos" class="rf-name" type="text" placeholder="%{getText('form.apellidos')}" required="true"></s:textfield>
	                            </div>
	                            <div class="rf-input-password rf-inputs">
	                            	<s:textfield name="cliente.pass" class="rf-password type_password" type="password" placeholder="%{getText('form.password')}" required="true"></s:textfield>
	                                <span class="show-pass"><s:text name="form.mostrar"></s:text></span>
                                </div>
                                <div class="rf-input-password rf-inputs">
	                            	<s:textfield name="repetirPass" class="rf-password type_password" type="password" placeholder="%{getText('form.repetir.password')}" required="true"></s:textfield>
	                                <span class="show-pass"><s:text name="form.mostrar"></s:text></span>
                                </div>
                            </div>
                            <div class="rf-checkbox-area">
                                <input type="checkbox">
                                <p>Sign me up for occasional newslettes and special offers</p>
                            </div>
                            <button type="submit"  class="register-btn hover-bs"><s:text name="form.alta"></s:text></button>
                        </s:form>
                    </div>
                    <div class="rf-body-right">
                        <button class="reg-by-fb-btn hover-bs">REGISTER WITH FACEBOOK</button>
                        <p>We wont post to Facebook withour your permission</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>