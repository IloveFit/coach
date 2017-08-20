<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="col-md-6">
	<script type="text/javascript">
		function modificarCoach(id, nombre, email, tipo, admin) {
			//limpiamos
			$("#idCoachMod").val('');
			$("#nombreCoachMod").val("");
			$("#mailCoachMod").val("");
			$("#tipoCoachMod").val('');
			$("#adminMod").prop("checked", false);
			
			//quitamos los mensajes de error
			$("span.laberError").remove();
			$("input").removeClass("inputError");
			
			//pasamos los valores al formulario
			$("#idCoachMod").val(id);
			$("#nombreCoachMod").val(nombre);
			$("#mailCoachMod").val(email);
			$("#tipoCoachMod").val(tipo);
			if(admin == 'S') {
				$("#adminMod").prop("checked", true);
			}
		}
	</script>

	<div class="box box-primary">
    	<div class="box-header with-border">
        	<h3 class="box-title">Alta Coach</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <s:form action="alta" namespace="/administrator/coach" theme="simple" enctype="multipart/form-data">
        	<div class="box-body">
        		<div class="form-group">
                	<label for="nombreCoach"><s:text name="form.nombre.apellidos"></s:text></label>
                	<s:textfield id="nombreCoach" key="form.nombre.apellidos" name="coach.nombre" required="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.nombre.apellidos')}"></s:textfield>
                	<s:fielderror fieldName="coach.nombre" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
            	<div class="form-group">
                	<label for="mailCoach"><s:text name="form.email"></s:text></label>
                	<s:textfield id="mailCoach" type="email" key="form.email" name="coach.email" required="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.email')}"></s:textfield>
                	<s:fielderror fieldName="coach.email" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                <div class="form-group">
                  	<label for="passCoach"><s:text name="form.password"></s:text></label>
                  	<s:textfield id="passCoach" type="password" name="coach.pass" class="form-control input-lg" cssErrorClass="inputError" required="true" maxlength="12"></s:textfield>
                  	<s:fielderror fieldName="coach.pass" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                <div class="form-group">
                  	<label for="passRepiteCoach"><s:text name="coach.password.nueva.repetir"></s:text></label>
                  	<s:textfield id="passRepiteCoach" name="repiteNuevaPass" class="form-control input-lg" cssErrorClass="inputError" type="password" required="true" maxlength="12"></s:textfield>
                	<s:fielderror fieldName="repiteNuevaPass" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                <div class="form-group">
                	<label for="tipoCoach"><s:text name="form.tipo.coach"></s:text></label>
                	<s:select list="%{#session.COMBO_TIPO_COACH}" name="coach.catalogo.id" listKey="id" listValue="nombre" class="form-control input-lg"></s:select>
                </div>
                <div class="form-group">
                  	<label for="avatar"><s:text name="form.avatar"></s:text></label>
                  	<s:file name="avatar" size="40" />

                  	<p class="help-block"><s:text name="form.avatar.ayuda"></s:text></p>
                </div>
                <div class="checkbox">
                  	<label>
                  		<s:checkbox name="admin"></s:checkbox><s:text name="form.administrador"></s:text>
                  	</label>
                </div>
			</div>
            <!-- /.box-body -->

			<div class="box-footer">
            	<button type="submit" class="btn btn-primary">Submit</button>
            </div>
		</s:form>
	</div>
    <!-- /.box -->
</div>

<div class="col-md-6">
	<div class="box box-primary">
    	<div class="box-header with-border">
        	<h3 class="box-title">Listado Coach</h3><br/><br/>
       		<div class="box">
           		<div class="box-header">
             			<h3 class="box-title">Elimina o modifica los Coach de la aplicación</h3>
           		</div>
           		<!-- /.box-header -->
           		<div class="box-body no-padding">
             			<table class="table table-striped">
               			<tr>
                 				<th style="width: 10px">ID</th>
                 				<th>Nombre</th>
                 				<th>Email</th>
                 				<th>Tipo</th>
                 				<th>Avatar</th>
                 				<th>Admin</th>
                 				<th>Acciones</th>
               			</tr>
             		<s:iterator value="listadoCoach" var="coach">
               			<tr>
               				<td><s:property value="id"/></td>
               				<td><s:property value="nombre"/></td>
               				<td><s:property value="email"/></td>
               				<td>
               					<s:select list="%{#session.COMBO_TIPO_COACH}" name="coach.tipo" listKey="id" listValue="nombre" class="form-control" value="%{#coach.catalogo.id}" disabled="true"></s:select>
               				</td>
               				<td>
               					<a href="<s:url value="%{#contextUrl}/imagenes/administrator/avatar/%{#coach.avatar}"/>" target="_blank">
               						<img src="<s:url value="%{#contextUrl}/imagenes/administrator/avatar/%{#coach.avatar}"/>" class="img-circle" style="max-width: 50px;" alt="User Image" />
               					</a>
               				</td>
               				<td class="text-green" style="font-size: 1.5em;">
               					<s:if test="%{#coach.admin == \"S\"}"><i class="icon fa fa-check"></i></s:if>
               				</td>
               				<td class="text-red" style="font-size: 1.5em;">
								<s:a action="eliminar" namespace="/administrator/coach" class="text-red" style="font-size: 1.5em;">
									<s:param name="coach.id" value="id"/>
									<i class="icon fa fa-trash"></i>
								</s:a>
               					<a class="text-red" style="font-size: 1.5em;" data-toggle="modal" data-target="#modificarCoachModal" onclick="modificarCoach('<s:property value="id"/>', '<s:property value="nombre"/>', '<s:property value="email"/>', '<s:property value="catalogo.id"/>', '<s:property value="admin"/>');">
									<i class="icon fa fa-edit"></i>
								</a>
               				</td>
               			</tr>	
               		</s:iterator>
					</table>
				</div>
           <!-- /.box-body -->
			</div>
        </div>
        <!-- Modal -->
		<div class="modal fade" id="modificarCoachModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title" id="myModalLabel">Modificar Coach</h4>
      				</div>
      				<s:form action="modificar" namespace="/administrator/coach" theme="simple" enctype="multipart/form-data">	
      					<s:hidden id="idCoachMod" name="coach.id"></s:hidden>
      					<div class="modal-body">
				        	<div class="box-body">
				        		<div class="form-group">
				                	<label for="nombreCoachMod"><s:text name="form.nombre.apellidos"></s:text></label>
				                	<s:textfield id="nombreCoachMod" key="form.nombre.apellidos" name="coach.nombre" required="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.nombre.apellidos')}"></s:textfield>
				                	<s:fielderror fieldName="coach.email" theme="ilovefit" class="fa fa-times-circle-o"/>
				                </div>
				            	<div class="form-group">
				                	<label for="mailCoachMod"><s:text name="form.email"></s:text></label>
				                	<s:textfield id="mailCoachMod" type="email" key="form.email" name="coach.email" required="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.email')}"></s:textfield>
				                	<s:fielderror fieldName="coach.email" theme="ilovefit" class="fa fa-times-circle-o"/>
				                </div>
				                <div class="form-group">
				                	<label for="tipoCoachMod"><s:text name="form.tipo.coach"></s:text></label>
				                	<s:select list="%{#session.COMBO_TIPO_COACH}" id="tipoCoachMod" name="coach.catalogo.id" listKey="id" listValue="nombre" class="form-control input-lg"></s:select>
				                </div>
				                <div class="form-group">
				                  	<label for="avatarCoachMod"><s:text name="form.avatar"></s:text></label>
				                  	<s:file id="avatarCoachMod" name="avatar" size="40" />
				
				                  	<p class="help-block"><s:text name="form.avatar.ayuda"></s:text></p>
				                </div>
				                <div class="checkbox">
				                  	<label>
				                  		<s:checkbox id="adminMod" name="admin"></s:checkbox><s:text name="form.administrador"></s:text>
				                  	</label>
				                </div>
							</div>
      					</div>
	      				<div class="modal-footer">
	        				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        				<button type="submit" class="btn btn-primary">Save changes</button>
	      				</div>
	      			</s:form>
          		</div>
  			</div>
		</div>
	</div>
</div>
         