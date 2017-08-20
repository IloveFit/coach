<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div class="col-md-6">
	<div class="box box-primary">
    	<div class="box-header with-border">
        	<h3 class="box-title">Alta rutina</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <s:form action="alta" namespace="/administrator/rutina" theme="simple">
        	<div id="bloqueAltaRutina" class="box-body">
        		<div class="form-group">
                	<label for="nombreRutina"><s:text name="form.nombre"></s:text></label>
                	<s:textfield id="nombreRutina" name="rutina.nombre" required="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.nombre')}"></s:textfield>
                	<s:fielderror fieldName="rutina.nombre" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                <div class="form-group row bloqueEjercicio" id="bloqueInicial">
                	<div class="col-xs-6">
						<label for="ejercicio"><s:text name="form.ejercicio"></s:text></label>
                		<s:select id="ejercicio" list="listaEjercicios" name="listaIdsEjercicios[0]" listKey="id" listValue="nombre" class="form-control input-lg"></s:select>                	
                	</div>
                	<div class="col-xs-5">
                		<label for="repeticionesEjercicio"><s:text name="form.repeticiones.duracion"></s:text></label>
	                	<s:textfield id="repeticionesEjercicio" name="listaDuracion[0]" required="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.repeticiones.duracion')}"></s:textfield>
	                	<s:fielderror fieldName="listaDuracion[0]" theme="ilovefit" class="fa fa-times-circle-o"/>
                	</div>
                </div>
                <h4 id="addEjercicio" onclick="addEjercicio('bloqueInicial', 'bloqueAltaRutina', 'bloqueEjercicio');"><i class="text-red fa fa-plus"></i> <span><s:text name="rutina.add.ejercicio" /></span></h4>
             <s:iterator value="listaDuracion" var="ejercicio" status="stat">
             	<s:if test="%{(#stat.count -1) > 0}">
          		<div class="form-group row bloqueEjercicio">
		              	<div class="col-xs-6">
						<label for="ejercicio"><s:text name="form.ejercicio"></s:text></label>
		            	<s:select id="ejercicio" list="listaEjercicios" name="listaIdsEjercicios[%{#stat.count - 1}]" listKey="id" listValue="nombre" class="form-control input-lg" value="%{listaIdsEjercicios[#stat.count - 1]}"></s:select>                	
		            </div>
		            <div class="col-xs-5">
		            	<label for="repeticionesEjercicio"><s:text name="form.repeticiones.duracion"></s:text></label>
		               	<s:textfield id="repeticionesEjercicio" name="listaDuracion[%{#stat.count -1}]" required="true" class="form-control input-lg" cssErrorClass="inputError" value="%{listaDuracion[#stat.count - 1]}"
		               	placeholder="%{getText('form.introduce.repeticiones.duracion')}"></s:textfield>
		               	<s:fielderror fieldName="listaDuracion[%{#stat.count - 1}]" theme="ilovefit" class="fa fa-times-circle-o"/>
		            </div>
		            <div class="col-xs-1" style="font-size: 1.5em; padding-top: 4%;">
		            	<i class="icon fa fa-trash text-red" onclick="$(this).parent().parent().remove(), recalcularNumeracion('bloqueEjercicio');"></i>
		            </div>
				</div>
			</s:if>
          	</s:iterator>
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
        	<h3 class="box-title">Listado de rutinas</h3><br/><br/>
       		<div class="box">
           		<div class="box-header">
             			<h3 class="box-title">Elimina o modifica las rutinas de la aplicación</h3>
           		</div>
           		<!-- /.box-header -->
           		<div class="box-body no-padding">
             			<table class="table table-striped">
               			<tr>
                 				<th style="width: 10px">ID</th>
                 				<th>Nombre</th>
                 				<th>Acciones</th>
               			</tr>
             		<s:iterator value="listadoRutina" var="rutina" status="status">
               			<tr>
               				<td><s:property value="id"/></td>
               				<td><s:property value="nombre"/></td>
               				<td class="text-red" style="font-size: 1.5em;">
								<s:a action="eliminar" namespace="/administrator/rutina" class="text-red" style="font-size: 1.5em;">
									<s:param name="rutina.id" value="id"/>
									<i class="icon fa fa-trash"></i>
								</s:a>
								<s:url namespace="/administrator/rutina" action="detallerutina" var="urldetalle">
									<s:param name="rutina.id" value="id"/>
								</s:url>
								<sj:a class="text-red" style="font-size: 1.5em;" id="detalleRutina%{id}" href="administrator/rutina/detallerutina?rutina.id=%{id}" targets="divDetalleRutina" onCompleteTopics="completeDetalleRutina">
								  	<s:param name="rutina.id" value="id"/>
								  	<i class="icon fa fa-edit"></i>
								</sj:a>
								
               					<a style="display: none;" data-toggle="modal" data-target="#modificarRutinaModal" id="enlaceModificarRutina">
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
        <div id="divDetalleRutina"></div>
	</div>
</div>
	<script type="text/javascript">
		function modificarRutina(id, nombre, descripcion) {
			//limpiamos
			$("#idRutinaMod").val('');
			$("#nombreRutinaMod").val("");
			
			//quitamos los mensajes de error
			$("span.laberError").remove();
			$("input").removeClass("inputError");
			
			//pasamos los valores al formulario
			$("#idRutinaMod").val(id);
			$("#nombreRutinaMod").val(nombre);
		}
		
		function addEjercicio(bloqueClonar, bloqueAppend, bloqueEjercicio, modificar) {
			var divEjercicio = $(document.createElement('div'))
	         .attr("class", 'col-xs-1')
	         .attr("id", "divEjercicio")
	         .attr("style", "font-size: 1.5em; padding-top: 4%;");
			
			var eliminarEjercicio = $(document.createElement('i'))
			.attr("class", "icon fa fa-trash text-red")
			.attr("onclick", "$(this).parent().parent().remove(), recalcularNumeracion('"+bloqueEjercicio+"');");
			
			//solo incluimos el eliminar en el alta
			if(modificar == undefined) {
				$(eliminarEjercicio).appendTo(divEjercicio);	
			}
			
			var ejercicioClonado = $('#'+bloqueClonar).clone().removeAttr("id");
			//inicializamos los valores
			$("input", ejercicioClonado).val("");
			$("select", ejercicioClonado).val("1");
			
			$(ejercicioClonado).append(divEjercicio).appendTo('#'+bloqueAppend);
			
			recalcularNumeracion(bloqueEjercicio);
		}
		
		/*
		$(document).ready(function(){
			$("#addEjercicio, #").click(function () {	
				
			});
		});
		*/
		
		function recalcularNumeracion (claseBloqueEjercicio) {
			//recalculamos la numeracion
			$("."+claseBloqueEjercicio ).each(function( i ) {
				var nameSelect = "listaIdsEjercicios[" + i + "]";
			    $("select", this).attr("name", nameSelect);
			    
			    var nameInput = "listaDuracion[" + i + "]";
			    $("input", this).attr("name", nameInput);
			});
		}
		
		$.subscribe('completeDetalleRutina', function(event,data) {
			$('#enlaceModificarRutina').click();
		});
	</script>   