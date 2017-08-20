<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="col-md-3">
<!-- Profile Image -->
	<div class="box box-primary">
    	<div class="box-body box-profile">
        	<img class="profile-user-img img-responsive img-circle" src="<s:url value="%{#contextUrl}/imagenes/administrator/avatar/%{#session.COACH_SESION.avatar}"/>" alt="Imagen de perfil">
			<h3 class="profile-username text-center"><s:text name="%{#session.COACH_SESION.nombre}"></s:text></h3>
			<p class="text-muted text-center"><s:text name="%{#session.COACH_SESION.catalogo.nombre}"></s:text></p>

           	<ul class="list-group list-group-unbordered">
             	<li class="list-group-item">
               		<b><s:text name="coach.clientes"></s:text></b> <a class="pull-right"><s:property value="numeroClientes"/></a>
             	</li>
             	<li class="list-group-item">
               		<b><s:text name="coach.mensajes"></s:text></b> <a class="pull-right"><s:property value="numeroMensajes"/></a>
             	</li>
           	</ul>
			<a href="#" class="btn btn-primary btn-block"><b>Follow</b></a>
		</div>
        <!-- /.box-body -->
	</div>
    <!-- /.box -->
</div>
<div class="col-md-9">
	<s:hidden name="tabSelected"></s:hidden>

	<div class="nav-tabs-custom">
    	<ul class="nav nav-tabs">
        	<li id="tab1"><a href="#settings" data-toggle="tab">Datos</a></li>
        	<li id="tab2"><a href="#activity" data-toggle="tab">Password</a></li>
            <li id="tab3"><a href="#timeline" data-toggle="tab">Avatar</a></li>
		</ul>
		<div class="tab-content">
			<div class="active tab-pane" id="settings">
				<s:form action="modificarDatos" namespace="/administrator/coach" theme="simple" class="form-horizontal">
					<s:hidden id="idCoachModDatos" name="coach.id" value="%{#session.COACH_SESION.id}"></s:hidden>
                	<div class="form-group">
                    	<label for="nombreCoach" class="col-sm-2 control-label"><s:text name="form.nombre.apellidos"></s:text></label>
                    	<div class="col-sm-10">
                      		<s:textfield id="nombreCoach" key="form.nombre.apellidos" name="coach.nombre" required="true" class="form-control" cssErrorClass="inputError" 
                      		placeholder="%{getText('form.introduce.nombre.apellidos')}" value="%{#session.COACH_SESION.nombre}"></s:textfield>
                			<s:fielderror fieldName="coach.nombre" theme="ilovefit" class="fa fa-times-circle-o"/>
                    	</div>
                  	</div>
                  	<div class="form-group">
                    	<label for="mailCoach" class="col-sm-2 control-label"><s:text name="form.email"></s:text></label>
                    	<div class="col-sm-10">
                      		<s:textfield id="mailCoach" type="email" key="form.email" name="coach.email" required="true" class="form-control" cssErrorClass="inputError" 
                   			placeholder="%{getText('form.introduce.email')}" value="%{#session.COACH_SESION.email}"></s:textfield>
                			<s:fielderror fieldName="coach.email" theme="ilovefit" class="fa fa-times-circle-o"/>
                    	</div>
                  	</div>
                  	<div class="form-group">
                    	<label for="tipCoach" class="col-sm-2 control-label"><s:text name="form.tipo.coach"></s:text></label>
                    	<div class="col-sm-10">
                      		<s:select id="tipCoach" list="%{#session.COMBO_TIPO_COACH}" name="coach.catalogo.id" listKey="id" listValue="nombre" class="form-control" value="%{#session.COACH_SESION.catalogo.id}"></s:select>
                    	</div>
                  	</div>
                  	<div class="form-group">
                    	<div class="col-sm-offset-2 col-sm-10">
                      		<button type="submit" class="btn btn-danger"><s:text name="form.modificar"></s:text></button>
                    	</div>
                  	</div>
				</s:form>
			</div>
            <!-- /.tab-pane -->
            <div class="tab-pane" id="activity">
				<s:form action="modificarPassword" namespace="/administrator/coach" theme="simple" class="form-horizontal">
					<s:hidden id="idCoachModDatos" name="coach.id" value="%{#session.COACH_SESION.id}"></s:hidden>
                	<div class="form-group">
                    	<label for="passAnteriorCoach" class="col-sm-2 control-label"><s:text name="coach.password.anterior"></s:text></label>
                    	<div class="col-sm-10">
                      		<s:textfield id="passAnteriorCoach" name="coach.pass" class="form-control" cssErrorClass="inputError" type="password" required="true" maxlength="12"></s:textfield>
                			<s:fielderror fieldName="coach.pass" theme="ilovefit" class="fa fa-times-circle-o"/>
                    	</div>
                  	</div>
                  	<div class="form-group">
                    	<label for="passNuevaCoach" class="col-sm-2 control-label"><s:text name="coach.password.nueva"></s:text></label>
                    	<div class="col-sm-10">
                      		<s:textfield id="passNuevaCoach" name="nuevaPass" class="form-control" cssErrorClass="inputError" type="password" required="true" maxlength="12"></s:textfield>
                			<s:fielderror fieldName="nuevaPass" theme="ilovefit" class="fa fa-times-circle-o"/>
                    	</div>
                  	</div>
                  	<div class="form-group">
                    	<label for="passRepiteCoach" class="col-sm-2 control-label"><s:text name="coach.password.nueva.repetir"></s:text></label>
                    	<div class="col-sm-10">
                      		<s:textfield id="passRepiteCoach" name="repiteNuevaPass" class="form-control" cssErrorClass="inputError" type="password" required="true" maxlength="12"></s:textfield>
                			<s:fielderror fieldName="repiteNuevaPass" theme="ilovefit" class="fa fa-times-circle-o"/>
                    	</div>
                  	</div>
                  	<div class="form-group">
                    	<div class="col-sm-offset-2 col-sm-10">
                      		<button type="submit" class="btn btn-danger"><s:text name="form.modificar"></s:text></button>
                    	</div>
                  	</div>
				</s:form>	
            </div>
			<!-- /.tab-pane -->
            <div class="tab-pane" id="timeline">
            	<img class="mod-profile-user-img img-responsive img-circle" src="<s:url value="%{#contextUrl}/imagenes/administrator/avatar/%{#session.COACH_SESION.avatar}"/>" alt="Imagen de perfil">
            	<s:form action="modificarAvatar" namespace="/administrator/coach" theme="simple" enctype="multipart/form-data" class="form-avatar">
            		<s:hidden id="idCoachModDatos" name="coach.id" value="%{#session.COACH_SESION.id}"></s:hidden>
            		<div class="form-group">
                  		<label for="avatar"><s:text name="form.avatar"></s:text></label>
                  		<s:file name="avatar" size="40" />

                  		<p class="help-block"><s:text name="form.avatar.ayuda"></s:text></p>
                	</div>
                	<div class="form-group">
                    	<div>
                      		<button type="submit" class="btn btn-danger"><s:text name="form.modificar"></s:text></button>
                    	</div>
                  	</div>
            	</s:form>    
			</div>
			<!-- /.tab-pane -->

            </div>
            <!-- /.tab-content -->
          </div>
          <!-- /.nav-tabs-custom -->
        	<script type="text/javascript">
        	$(document).ready(function() {
        	    if ($("#tabSelected").val() == "2") {
        	    	$("#tab2 a").click();	
        	    } else if ($("#tabSelected").val() == "3") {
        	    	$("#tab3 a").click();	
        	    } else {
        	    	$("#tab1 a").click(); 
        	    }
        	});
        	</script>
        </div>
        <!-- /.col -->
        
        