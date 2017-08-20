<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<div class="col-md-6">
	<div class="box box-primary">
    	<div class="box-header with-border">
        	<h3 class="box-title">Alta plan</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <s:form action="alta" namespace="/administrator/plan" theme="simple">
        	<div id="bloqueAltaPlan" class="box-body">
        		<div class="form-group">
                	<label for="nombrePlan"><s:text name="form.nombre"></s:text></label>
                	<s:textfield id="nombrePlan" name="plan.nombre" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.nombre')}" required="true" maxlength="45"></s:textfield>
                	<s:fielderror fieldName="plan.nombre" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                <div class="form-group">
                	<label for="descPlan"><s:text name="form.descripcion"></s:text></label>
                	<s:textarea id="descPlan" name="plan.descripcion" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.descripcion')}"
                	 required="true" maxlength="1000"></s:textarea>
                	<s:fielderror fieldName="plan.descripcion" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                <div class="form-group row">
                	<div class="col-xs-6">
	                	<label for="tipoNivel"><s:text name="form.tipo.nivel"></s:text></label>
	                	<s:select id="tipoNivel" list="%{#session.COMBO_NIVEL}" name="plan.catalogoByNivel.id" listKey="id" listValue="nombre" class="form-control input-lg"></s:select>
                	</div>
                	<div class="col-xs-6">
						<label for="tipoObjetivo"><s:text name="form.tipo.objetivo"></s:text></label>
                		<s:select id="tipoObjetivo" list="%{#session.COMBO_OBJETIVO}" name="plan.catalogoByObjetivo.id" listKey="id" listValue="nombre" class="form-control input-lg"></s:select>                	
                	</div>
                </div>
                <div class="form-group">
                	<label for="tipoMaquina"><s:text name="form.tipo.maquina"></s:text></label>
                	<s:select id="tipoMaquina" list="%{#session.COMBO_TIP_MAQUINA}" name="plan.catalogoByMaquina.id" listKey="id" listValue="nombre" class="form-control input-lg"></s:select>
                </div>
                <div class="form-group row bloqueRutina" id="bloqueInicial">
                	<div class="col-xs-11">
						<label for="rutina"><s:text name="form.rutina"></s:text></label>
                		<s:select id="rutina" list="listaRutinas" name="listaIdsRutinas[0]" listKey="id" listValue="nombre" class="form-control input-lg"></s:select>                	
                	</div>
                </div>
                <h4 id="addRutina" onclick="addRutina('bloqueInicial', 'bloqueAltaPlan', 'bloqueRutina');"><i class="text-red fa fa-plus"></i> <span><s:text name="plan.add.rutina" /></span></h4>
             <s:iterator value="listaIdsRutinas" var="rutina" status="stat">
             	<s:if test="%{(#stat.count -1) > 0}">
          		<div class="form-group row bloqueRutina">
					<div class="col-xs-6">
						<label for="ejercicio"><s:text name="form.rutina"></s:text></label>
		            	<s:select id="ejercicio" list="listaRutinas" name="listaIdsRutinas[%{#stat.count - 1}]" listKey="id" listValue="nombre" class="form-control input-lg" value="%{listaIdsRutinas[#stat.count - 1]}"></s:select>                	
		            </div>
		            <div class="col-xs-1" style="font-size: 1.5em; padding-top: 4%;">
		            	<i class="icon fa fa-trash text-red" onclick="$(this).parent().parent().remove(), recalcularNumeracion('bloqueRutina');"></i>
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
        	<h3 class="box-title">Listado de planes</h3><br/><br/>
       		<div class="box">
           		<div class="box-header">
             			<h3 class="box-title">Elimina o modifica los planes de la aplicación</h3>
           		</div>
           		<!-- /.box-header -->
           		<div class="box-body no-padding">
             			<table class="table table-striped">
               			<tr>
                 				<th style="width: 10px">ID</th>
                 				<th>Nombre</th>
                 				<th>Descripción</th>
                 				<th>Nivel</th>
                 				<th>Objetivo</th>
                 				<th>Máquina</th>
                 				<th>Acciones</th>
               			</tr>
             		<s:iterator value="listadoPlan" var="plan">
               			<tr>
               				<td><s:property value="id"/></td>
               				<td><s:property value="nombre"/></td>
               				<td>
               					<s:if test="%{descripcion.length > 100}">
               						<s:property value="descripcion.substring(0,100)"/>...
               					</s:if>
               					<s:if test="%{descripcion.length < 101}">
               						<s:property value="descripcion"/>
               					</s:if>
               				</td>
               				<td>
               					<s:select list="%{#session.COMBO_NIVEL}" name="plan.catalogoByNivel.id" listKey="id" listValue="nombre" class="form-control" value="%{#plan.catalogoByNivel.id}" disabled="true" style="min-width: 125px;"></s:select>
               				</td>
               				<td>
               					<s:select list="%{#session.COMBO_OBJETIVO}" name="plan.catalogoByObjetivo.id" listKey="id" listValue="nombre" class="form-control" value="%{#plan.catalogoByObjetivo.id}" disabled="true" style="min-width: 125px;"></s:select>
               				</td>
               				<td>
               					<s:select list="%{#session.COMBO_TIP_MAQUINA}" name="plan.catalogoByMaquina.id" listKey="id" listValue="nombre" class="form-control" value="%{#plan.catalogoByMaquina.id}" disabled="true" style="min-width: 125px;"></s:select>
               				</td>
               				<td class="text-red" style="font-size: 1.5em;">
								<s:a action="eliminar" namespace="/administrator/plan" class="text-red" style="font-size: 1.5em;">
									<s:param name="plan.id" value="id"/>
									<i class="icon fa fa-trash"></i>
								</s:a>
								<sj:a class="text-red" style="font-size: 1.5em;" id="detallePlan%{id}" href="administrator/plan/detallePlan?plan.id=%{id}" targets="divDetallePlan" onCompleteTopics="completeDetallePlan">
								  	<i class="icon fa fa-edit"></i>
								</sj:a>
								
               					<a style="display: none;" data-toggle="modal" data-target="#modificarPlanModal" id="enlaceModificarPlan">
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
        <div id="divDetallePlan"></div>
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
		
		function addRutina(bloqueClonar, bloqueAppend, bloqueRutina, modificar) {
			var divRutina = $(document.createElement('div'))
	         .attr("class", 'col-xs-1')
	         .attr("id", "divRutina")
	         .attr("style", "font-size: 1.5em; padding-top: 4%;");
			
			var eliminarRutina = $(document.createElement('i'))
			.attr("class", "icon fa fa-trash text-red")
			.attr("onclick", "$(this).parent().parent().remove(), recalcularNumeracion('"+bloqueRutina+"');");
			
			//solo incluimos el eliminar en el alta
			if(modificar == undefined) {
				$(eliminarRutina).appendTo(divRutina);	
			}
			
			var rutinaClonado = $('#'+bloqueClonar).clone().removeAttr("id");
			//inicializamos los valores
			$("input", rutinaClonado).val("");
			$("select", rutinaClonado).removeAttr("value");
			
			$(rutinaClonado).append(divRutina).appendTo('#'+bloqueAppend);
			
			recalcularNumeracion(bloqueRutina);
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
				var nameSelect = "listaIdsRutinas[" + i + "]";
			    $("select", this).attr("name", nameSelect);
			});
		}
		
		$.subscribe('completeDetallePlan', function(event,data) {
			$('#enlaceModificarPlan').click();
		});
	</script>   