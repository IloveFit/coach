<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- Using Struts2 Tags in JSP --%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html lang="en">
	<head>
	<% String contexto = request.getContextPath();  %>
    <!--- Basic Page Needs  -->
	    <meta charset="utf-8">
	    <title><tiles:insertAttribute name="title" /></title>
	    <meta name="description" content="">
	    <meta name="author" content="">
	    <meta name="keywords" content="">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <!-- Mobile Specific Meta  -->
	    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	    <!-- Google Fonts -->
	    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600" rel="stylesheet">
	    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
	    <!-- CSS -->
	    <link rel="stylesheet" href="<%=contexto%>/css/ilovefitcoach/bootstrap.min.css">
	    <link rel="stylesheet" href="<%=contexto%>/css/ilovefitcoach/font-awesome.min.css">
	    <link rel="stylesheet" href="<%=contexto%>/css/ilovefitcoach/owl.carousel.min.css">
	    <link rel="stylesheet" href="<%=contexto%>/css/ilovefitcoach/animate.css">
	    <link rel="stylesheet" href="<%=contexto%>/css/ilovefitcoach/meanmenu.min.css">
	    <link rel="stylesheet" href="<%=contexto%>/css/ilovefitcoach/magnific-popup.css">
	    <link rel="stylesheet" href="<%=contexto%>/css/ilovefitcoach/typography.css">
	    <link rel="stylesheet" href="<%=contexto%>/css/ilovefitcoach/style.css">
	    <link rel="stylesheet" href="<%=contexto%>/css/ilovefitcoach/responsive.css">
	    
	    <link rel="stylesheet" href="<%=contexto%>/css/ilovefitcoach/ilovefitcoach.css">
	    <!-- Favicon -->
	    <link rel="shortcut icon" type="image/png" href="img/favicon.ico">
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	        <![endif]-->
	</head>
	
	<body data-spy="scroll" data-target="#scroll-menu" data-offset="100">
	    <!-- preloader -->
	    <div id="preloader"></div>
	    <!-- header-start -->
	    <header>
	        <div class="header-top">
	            <div class="container">
	                <div class="row">
	                	<tiles:insertAttribute name="headerIcons" />
	                	<tiles:insertAttribute name="headerLinks" />
	                </div>
				</div>
			</div>
			<div class="menu-area hidden-sm hidden-xs">
	            <div class="container">
	                <div class="row">
	                    <div class="col-md-3">
	                        <div class="logo">
	                            <a href="index.html"><img src="imagenes/ilovefitcoach/logo.png" alt=""></a>
	                        </div>
	                    </div>
	                    <div class="col-md-7">
	                    	<tiles:insertAttribute name="menu" />
	                    </div>
	                    <div class="col-md-2">
	                    	<tiles:insertAttribute name="buscador" />
	                    </div>
					</div>
				</div>
			</div>
			<div class="mobile-menu-area visible-xs visible-sm">
				<tiles:insertAttribute name="menuMovil" />
			</div>
		</header>
		<!-- header-end -->
    <!-- hero-section-start -->
    <div class="hero-section" id="home">
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <div class="hero-content">
                    	<tiles:insertAttribute name="heroContent" />
                    </div>
                </div>
            </div>
            <tiles:insertAttribute name="crumbs" />
        </div>
    </div>
    <!-- hero-section-end -->
    <tiles:insertAttribute name="content" />
    <tiles:insertAttribute name="subscribe" />
    <tiles:insertAttribute name="footer" />
    
    <!-- Scripts -->
    <script src="<%=contexto%>/js/ilovefitcoach/jquery-3.2.0.min.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/owl.carousel.min.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/jquery.counterup.min.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/countdown.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/jquery.meanmenu.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/jquery.magnific-popup.min.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/jquery.scrollUp.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/jquery.mixitup.min.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/jquery.nstSlider.min.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/handleCounter.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/jquery.waypoints.min.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/bootstrap.min.js"></script>
    <script src="<%=contexto%>/js/ilovefitcoach/theme.js"></script>
</html>