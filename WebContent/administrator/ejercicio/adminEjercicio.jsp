<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>

<style>
   .flowplayer { min-width: 250px; }
</style>

<div class="col-md-6">
	<div class="box box-primary">
    	<div class="box-header with-border">
        	<h3 class="box-title">Alta ejercicio</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <s:form action="alta" namespace="/administrator/ejercicio" theme="simple" enctype="multipart/form-data">
        	<div class="box-body">
        		<div class="form-group">
                	<label for="nombreEjercicio"><s:text name="form.nombre"></s:text></label>
                	<s:textfield id="nombreEjercicio" name="ejercicio.nombre" maxlength="45" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.nombre')}"></s:textfield>
                	<s:fielderror fieldName="ejercicio.nombre" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
            	<div class="form-group">
                	<label for="descEjercicio"><s:text name="form.descripcion"></s:text></label>
                	<s:textarea id="descEjercicio" name="ejercicio.descripcion" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.descripcion')}" rows="5"></s:textarea>
                	<s:fielderror fieldName="ejercicio.descripcion" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                <div class="form-group">
                	<label for="tipoEjercicio"><s:text name="form.tipo.ejercicio"></s:text></label>
                	<s:select id="tipoEjercicio" list="%{#session.COMBO_TIPO_EJERCICIO}" name="ejercicio.catalogo.id" listKey="id" listValue="nombre" class="form-control input-lg" onchange="mostrarDuracionRepet();"></s:select>
                </div>
                <!-- 
                <div class="form-group" id="bloqueRepeticiones">
                	<label for="repeticionesEjercicio"><s:text name="form.repeticiones"></s:text></label>
                	<s:textfield id="repeticionesEjercicio" name="ejercicio.repeticiones" requiredLabel="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.repeticiones')}"></s:textfield>
                	<s:fielderror fieldName="ejercicio.repeticiones" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                <div class="form-group" id="bloqueDuracion">
                	<label for="duracionEjercicio"><s:text name="form.minutos"></s:text></label>
                	<s:textfield id="duracionEjercicio" name="ejercicio.duracion" requiredLabel="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.duracion')}"></s:textfield>
                	<s:fielderror fieldName="ejercicio.duracion" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                 -->
                <div class="form-group">
                  	<label for="video"><s:text name="form.video"></s:text></label>
                  	<s:file id="video" name="video" size="40" />

                  	<p class="help-block"><s:text name="form.avatar.ayuda"></s:text></p>
                </div>
			</div>
            <!-- /.box-body -->

			<div class="box-footer">
            	<button type="submit" class="btn btn-primary"><s:text name="form.alta"></s:text></button>
            </div>
		</s:form>
	</div>
    <!-- /.box -->
</div>

<div class="col-md-6">
	<div class="box box-primary">
    	<div class="box-header with-border">
        	<h3 class="box-title">Listado de ejercicios</h3><br/><br/>
       		<div class="box">
           		<div class="box-header">
             			<h3 class="box-title">Elimina o modifica los ejercicios de la aplicación</h3>
           		</div>
           		<!-- /.box-header -->
           		<div class="box-body no-padding">
             			<table class="table table-striped">
               			<tr>
                 				<th style="width: 10px">ID</th>
                 				<th>Nombre</th>
                 				<th>Descripción</th>
                 				<th>Tipo de ejercicio</th>
                 				<th>Video</th>
                 				<th>Acciones</th>
               			</tr>
             		<s:iterator value="listadoEjercicio" var="ejercicio">
               			<tr>
               				<td><s:property value="id"/></td>
               				<td><s:property value="nombre"/></td>
               				<td title="<s:property value="descripcion"/>">
               					<s:if test="%{descripcion.length > 100}">
               						<s:property value="descripcion.substring(0,100)"/>...
               					</s:if>
               					<s:if test="%{descripcion.length < 101}">
               						<s:property value="descripcion"/>
               					</s:if>
               				</td>
               				<td>
               					<s:select list="%{#session.COMBO_TIPO_EJERCICIO}" name="ejercicio.catalogo.id" listKey="id" listValue="nombre" class="form-control" value="%{#ejercicio.catalogo.id}" disabled="true" style="min-width: 125px;"></s:select>
               				</td>
               				<td>
               					<div class="flowplayer" data-swf="flowplayer.swf" data-ratio="0.4167" data-logo="//flowplayer.com/media/img/mylogo.png">
							      	<video controls="controls" width="200" height="200">
							         	<source type="video/mp4" src="<s:url value="%{#contextUrl}/imagenes/administrator/videos/%{#ejercicio.video}"/>">
							      	</video>
							  	</div>
               				</td>
               				<td class="text-red" style="font-size: 1.5em;">
								<s:a action="eliminar" namespace="/administrator/ejercicio" class="text-red" style="font-size: 1.5em;">
									<s:param name="ejercicio.id" value="id"/>
									<i class="icon fa fa-trash"></i>
								</s:a>
               					<a class="text-red" style="font-size: 1.5em;" data-toggle="modal" data-target="#modificarEjercicioModal" onclick="modificarEjercicio('<s:property value="id"/>', '<s:property value="nombre"/>', '<s:property value="descripcion"/>', '<s:property value="tipoEjercicio"/>');">
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
		<div class="modal fade" id="modificarEjercicioModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
      				<div class="modal-header">
        				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        				<h4 class="modal-title" id="myModalLabel">Modificar ejercicio</h4>
      				</div>
      				<s:form action="modificar" namespace="/administrator/ejercicio" theme="simple" enctype="multipart/form-data">
      					<s:hidden id="idEjercicioMod" name="ejercicio.id"></s:hidden>
			        	<div class="box-body">
			        		<div class="form-group">
			                	<label for="nombreEjercicioMod"><s:text name="form.nombre"></s:text></label>
			                	<s:textfield id="nombreEjercicioMod" key="form.nombre" name="ejercicio.nombre" maxlength="45" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.nombre.apellidos')}"></s:textfield>
			                	<s:fielderror fieldName="ejercicio.nombre" theme="ilovefit" class="fa fa-times-circle-o"/>
			                </div>
			            	<div class="form-group">
			                	<label for="descripcionEjercicioMod"><s:text name="form.descripcion"></s:text></label>
			                	<s:textarea id="descripcionEjercicioMod" name="ejercicio.descripcion" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.descripcion')}"></s:textarea>
			                	<s:fielderror fieldName="ejercicio.descripcion" theme="ilovefit" class="fa fa-times-circle-o"/>
			                </div>
			                <div class="form-group">
			                	<label for="tipoEjercicioMod"><s:text name="form.tipo.ejercicio"></s:text></label>
			                	<s:select id="tipoEjercicioMod" list="%{#session.COMBO_TIPO_EJERCICIO}" name="ejercicio.catalogo.id" listKey="id" listValue="nombre" class="form-control input-lg"></s:select>
			                </div>
			                <div class="form-group">
			                  	<label for="video"><s:text name="form.avatar"></s:text></label>
			                  	<s:file id="video" name="video" size="40" />
			                  	<s:fielderror fieldName="video" theme="ilovefit" class="fa fa-times-circle-o"/>
			
			                  	<p class="help-block"><s:text name="form.video.ayuda"></s:text></p>
			                </div>
						</div>
	      				<div class="modal-footer">
	        				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        				<button type="submit" class="btn btn-primary"><s:text name="form.modificar"></s:text></button>
	      				</div>
	      			</s:form>
          		</div>
  			</div>
		</div>
	</div>
</div>
	<script type="text/javascript">
		function modificarEjercicio(id, nombre, descripcion, tipo) {
			//limpiamos
			$("#idEjercicioMod").val('');
			$("#nombreEjercicioMod").val("");
			$("#descripcionEjercicioMod").val("");
			$("#tipoEjercicioMod").val('');
			
			//quitamos los mensajes de error
			$("span.laberError").remove();
			$("input").removeClass("inputError");
			
			//pasamos los valores al formulario
			$("#idEjercicioMod").val(id);
			$("#nombreEjercicioMod").val(nombre);
			$("#descripcionEjercicioMod").val(descripcion);
			$("#tipoEjercicioMod").val(tipo);
		}
		
		/**
		function mostrarDuracionRepet () {
			if ($("#tipoEjercicio").val() == 1) {
				$("#bloqueRepeticiones").hide();
				$("#bloqueDuracion").show();
			}
			if ($("#tipoEjercicio").val() == 2) {
				$("#bloqueRepeticiones").show();
				$("#bloqueDuracion").hide();
			}
		}
		**/
		
		$(document).ready(function() {
			mostrarDuracionRepet();
		});
		
		flowplayer(function (api, root) {
			//quitamos el boton de compartir, chromecast
			$(".fp-share").hide();
			//$(".fp-controls").hide();
			$(".fp-ui>.fp-play, .fp-ui>.fp-pause", root).remove();
		});
	</script>   