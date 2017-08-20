<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="col-lg-3 col-xs-6">
	<!-- small box -->
	<div class="small-box bg-aqua">
		<div class="inner">
			<h3><s:text name="numeroPlanesTop"></s:text></h3>
			<p>Planes configurados</p>
		</div>
		<div class="icon">
			<i class="fa fa-calendar-check-o"></i>
		</div>
		<s:a namespace="/administrator/plan" action="registro" class="small-box-footer">Administrar planes <i class="fa fa-arrow-circle-right"></i></s:a>
	</div>
</div>
<!-- ./col -->
<div class="col-lg-3 col-xs-6">
	<!-- small box -->
    <div class="small-box bg-green">
    	<div class="inner">
        	<h3><s:text name="numeroPlanesTop"></s:text></h3>
			<p>Mensajes recibidos</p>
		</div>
        <div class="icon">
        	<i class="fa fa-envelope-o"></i>
		</div>
		<s:a namespace="/administrator/mensajes" action="inicio" class="small-box-footer">Ver mis mensajes <i class="fa fa-arrow-circle-right"></i></s:a>
	</div>
</div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3><s:text name="numeroClientesTop"></s:text></h3>

              <p>Usuarios registrados</p>
            </div>
            <div class="icon">
              <i class="ion ion-ios-people-outline"></i>
            </div>
            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-red">
            <div class="inner">
              <h3>65</h3>

              <p>Unique Visitors</p>
            </div>
            <div class="icon">
              <i class="ion ion-pie-graph"></i>
            </div>
            <a href="#" class="small-box-footer">More info <i class="fa fa-arrow-circle-right"></i></a>
          </div>
        </div>
</div>