<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>

<s:include value="head.jsp"></s:include>


	<body class="hold-transition login-page">
		<div class="login-box">
		  	<div class="login-logo">
		    	<a href="../../index2.html"><b>ILoveFit</b>COACH</a>
		  	</div>
		  	<s:if test="hasActionErrors()">
				<div class="alert alert-danger alert-dismissible">
                	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                	<h4><i class="icon fa fa-ban"></i> Error!</h4>
                	<s:actionerror/>
              	</div>
			</s:if>
		  	<!-- /.login-logo -->
		  	<div class="login-box-body">
		    	<p class="login-box-msg"><s:text name="login.text.arriba"></s:text></p>
		
		    	<s:form action="loginCoach" theme="simple">
		      		<div class="form-group has-feedback">
		      			<s:textfield type="email" class="form-control" placeholder="Email" key="usuario.nombre" name="coach.email" cssErrorClass="inputError"></s:textfield>
		        		<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
		        		<s:fielderror fieldName="coach.email" theme="ilovefit" class="fa fa-times-circle-o"/>
		      		</div>
		      		<div class="form-group has-feedback">
				        <s:textfield key="usuario.password" name="coach.pass" type="password" class="form-control" placeholder="Password" cssErrorClass="inputError"></s:textfield>
				        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
				        <s:fielderror fieldName="coach.email" theme="ilovefit" class="fa fa-times-circle-o"/>
		      		</div>
		      		<div class="row">
		        		<div class="col-xs-8">
			          		<div class="checkbox icheck">
			            		<label>
			              			<input type="checkbox"> <s:text name="login.recordar" />
			            		</label>
			          		</div>
			        	</div>
			        	<!-- /.col -->
			        	<div class="col-xs-4">
			          		<button type="submit" class="btn btn-primary btn-block btn-flat"><s:text name="login.entrar" /></button>
			        	</div>
			        	<!-- /.col -->
		      		</div>
		    	</s:form>
		
		    	<div class="social-auth-links text-center">
		      		<p>- OR -</p>
		      		<a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-facebook"></i> Sign in using
		        	Facebook</a>
		      		<a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using
		        	Google+</a>
		    	</div>
		    	<!-- /.social-auth-links -->
		
			    <a href="#">I forgot my password</a><br>
			    <a href="register.html" class="text-center">Register a new membership</a>
		
		  	</div>
		  	<!-- /.login-box-body -->
		</div>
		<!-- /.login-box -->
		
		<!-- jQuery 2.2.3 -->
		<script src="../js/administrator/plugins/jQuery/jquery-2.2.3.min.js"></script>
		<!-- Bootstrap 3.3.6 -->
		<script src="../js/administrator/bootstrap/js/bootstrap.min.js"></script>
		<!-- iCheck -->
		<script src="../js/administrator/plugins/iCheck/icheck.min.js"></script>
		<script>
		  $(function () {
		    $('input').iCheck({
		      checkboxClass: 'icheckbox_square-blue',
		      radioClass: 'iradio_square-blue',
		      increaseArea: '20%' // optional
		    });
		  });
		</script>
	</body>
</html>