<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
	<head>
		<% String contexto = request.getContextPath();  %>
	
		<!-- jQuery 2.2.3 -->
		<script src="<%=contexto%>/js/administrator/plugins/jQuery/jquery-2.2.3.min.js"></script>
	
		<sj:head jqueryui="true" jquerytheme="cupertino"/>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title><tiles:getAsString name="title" /></title>
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<!-- Bootstrap 3.3.6 -->
		<link rel="stylesheet" href="<%=contexto%>/css/administrator/bootstrap/css/bootstrap.min.css">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
		<!-- Ionicons -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
		<!-- Theme style -->
		<link rel="stylesheet" href="<%=contexto%>/css/administrator/dist/css/AdminLTE.css">
		<!-- AdminLTE Skins. Choose a skin from the css/skins
		     folder instead of downloading all of them to reduce the load. -->
		<link rel="stylesheet" href="<%=contexto%>/css/administrator/dist/css/skins/_all-skins.min.css">
		<!-- iCheck -->
		<link rel="stylesheet" href="<%=contexto%>/css/administrator/plugins/iCheck/flat/blue.css">
		<!-- Morris chart -->
		<link rel="stylesheet" href="<%=contexto%>/css/administrator/plugins/morris/morris.css">
		<!-- jvectormap -->
		<link rel="stylesheet" href="<%=contexto%>/css/administrator/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
		<!-- Date Picker -->
		<link rel="stylesheet" href="<%=contexto%>/css/administrator/plugins/datepicker/datepicker3.css">
		<!-- Daterange picker -->
		<link rel="stylesheet" href="<%=contexto%>/css/administrator/plugins/daterangepicker/daterangepicker.css">
		<!-- bootstrap wysihtml5 - text editor -->
		<link rel="stylesheet" href="<%=contexto%>/css/administrator/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
		 <!-- select2 -->
  		<link rel="stylesheet" href="<%=contexto%>/css/administrator/plugins/select2/select2.css">
		<!-- reproductor de video -->
		<link rel="stylesheet" href="<%=contexto%>/css/video/skin.css">
		<!-- Custom css -->
		<link rel="stylesheet" href="<%=contexto%>/css/administrator/custom.css">
		
		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
	</head>
	<body class="hold-transition skin-blue sidebar-mini">
		<!-- Bootstrap 3.3.6 -->
		<script src="<%=contexto%>/js/administrator/bootstrap/js/bootstrap.min.js"></script>
		<!-- FastClick -->
		<script src="<%=contexto%>/js/administrator/plugins/fastclick/fastclick.js"></script>
		<!-- select2 -->
		<script src="<%=contexto%>/js/administrator/plugins/select2/select2.full.js"></script>
		<!-- AdminLTE App -->
		<script src="<%=contexto%>/js/administrator/app.js"></script>
		<!-- AdminLTE for demo purposes -->
		<script src="<%=contexto%>/js/administrator/demo.js"></script>
		<!-- reproductor de video -->
		<script src="<%=contexto%>/js/videos/flowplayer.js"></script>
		<div class="wrapper">
			<tiles:insertAttribute name="header" />
			
			<tiles:insertAttribute name="menu" />
 
 			<!-- Content Wrapper. Contains page content -->
  			<div class="content-wrapper">
    			<!-- Content Header (Page header) -->
   				<section class="content-header">
					<h1><tiles:insertAttribute name="titleContent" /></h1>
				</section>
				
				<!-- Main content -->
    			<section class="content">
    				<tiles:insertAttribute name="top" />
    				
    				<!-- Errores -->
					<s:if test="hasActionErrors()">
						<br/>
						<div class="alert alert-danger alert-dismissible">
		                	<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
		                	<h4><i class="icon fa fa-ban"></i> Error!</h4>
		                	<s:actionerror/>
		              	</div>
					</s:if>
					<!-- mensajes -->
					<s:if test="hasActionMessages()">
						<br/>
						<div class="alert alert-success alert-dismissible">
			                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
			                <h4><i class="icon fa fa-check"></i> Info!</h4>
		                	<s:actionmessage/>
		              	</div>
					</s:if>
    				
      				<div class="row">
						<tiles:insertAttribute name="content" />
					</div>
				</section>
			</div>
			<tiles:insertAttribute name="footer" />
		</div>
		<script type="text/javascript">
		$(function () {
		    //Initialize Select2 Elements
		    $(".select2").select2();
		});
		</script>
	</body>
</html>