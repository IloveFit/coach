<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!-- Modal -->
<div class="modal fade" id="modificarRutinaModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
	 		<div class="modal-header">
	   			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	   			<h4 class="modal-title" id="myModalLabel">Modificar rutina</h4>
	 		</div>
	 		<s:form action="modificar" namespace="/administrator/rutina" theme="simple">
	 			<s:hidden id="idRutinaMod" name="rutina.id"></s:hidden>
	      		<div id="bloqueModRutina" class="box-body">
		      		<div class="form-group">
		              	<label for="nombreRutinaMod"><s:text name="form.nombre"></s:text></label>
		              	<s:textfield id="nombreRutinaMod" key="form.nombre" name="rutina.nombre" required="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.nombre')}"></s:textfield>
		              	<s:fielderror fieldName="rutina.nombre" theme="ilovefit" class="fa fa-times-circle-o"/>
		          	</div>
	          	<s:iterator value="listRutinaEjercicio" var="rutinaEjercicio" status="stat">
	          		<div class="form-group row bloqueEjercicioMod" id="bloqueInicialMod">
			              	<div class="col-xs-6">
							<label for="ejercicioMod"><s:text name="form.ejercicio"></s:text></label>
			            	<s:select id="ejercicioMod" list="listaEjercicios" name="listaIdsEjercicios[%{#stat.count - 1}]" listKey="id" listValue="nombre" class="form-control input-lg" value="%{ejercicio.id}"></s:select>                	
			            </div>
			            <div class="col-xs-5">
			            	<label for="repeticionesEjercicioMod"><s:text name="form.repeticiones.duracion"></s:text></label>
			               	<s:textfield id="repeticionesEjercicioMod" name="listaDuracion[%{#stat.count -1}]" requiredLabel="true" class="form-control input-lg" cssErrorClass="inputError" value="%{duracionRepet}"
			               	placeholder="%{getText('form.introduce.repeticiones.duracion')}" required="true"></s:textfield>
			               	<s:fielderror fieldName="listaDuracion[%{#stat.count - 1}]" theme="ilovefit" class="fa fa-times-circle-o"/>
			            </div>
			            <div class="col-xs-1" style="font-size: 1.5em; padding-top: 4%;">
			            	<i class="icon fa fa-trash text-red" onclick="$(this).parent().parent().remove(), recalcularNumeracion('bloqueEjercicioMod');"></i>
			            </div>
					</div>
	          	</s:iterator>
		            <h4  onclick="addEjercicio('bloqueInicialMod', 'bloqueModRutina', 'bloqueEjercicioMod', 'true');"><i class="text-red fa fa-plus"></i> <span><s:text name="rutina.add.ejercicio" /></span></h4>
				</div>
	  			<div class="modal-footer">
	    			<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
	    			<button type="submit" class="btn btn-primary"><s:text name="form.modificar"></s:text></button>
	  			</div>
			</s:form>
		</div>
	</div>
 </div>
