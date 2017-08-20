<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!-- Modal -->
<div class="modal fade" id="modificarPlanModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
	 		<div class="modal-header">
	   			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	   			<h4 class="modal-title" id="myModalLabel">Modificar plan</h4>
	 		</div>
	 		<s:form action="modificar" namespace="/administrator/plan" theme="simple">
	 			<s:hidden id="idPlanMod" name="plan.id"></s:hidden>
	      		<div id="bloqueModPlan" class="box-body">
		      		<div class="form-group">
		              	<label for="nombrePlanMod"><s:text name="form.nombre"></s:text></label>
		              	<s:textfield id="nombrePlanMod" key="form.nombre" name="plan.nombre" required="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.nombre')}"></s:textfield>
		              	<s:fielderror fieldName="plan.nombre" theme="ilovefit" class="fa fa-times-circle-o"/>
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
	          	<s:iterator value="listPlanRutina" var="planRutina" status="stat">
	          		<div class="form-group row bloqueRutinaMod" id="bloqueInicialMod">
			              	<div class="col-xs-11">
							<label for="rutinaMod"><s:text name="form.rutina"></s:text></label>
			            	<s:select id="rutinaMod" list="listaRutinas" name="listaIdsRutinas[%{#stat.count - 1}]" listKey="id" listValue="nombre" class="form-control input-lg" value="%{rutina.id}"></s:select>                	
			            </div>
			            <div class="col-xs-1" style="font-size: 1.5em; padding-top: 4%;">
			            	<i class="icon fa fa-trash text-red" onclick="$(this).parent().parent().remove(), recalcularNumeracion('bloqueRutinaMod');"></i>
			            </div>
					</div>
	          	</s:iterator>
		            <h4  onclick="addRutina('bloqueInicialMod', 'bloqueModPlan', 'bloqueRutinaMod', 'true');"><i class="text-red fa fa-plus"></i> <span><s:text name="plan.add.rutina" /></span></h4>
				</div>
	  			<div class="modal-footer">
	    			<button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
	    			<button type="submit" class="btn btn-primary"><s:text name="form.modificar"></s:text></button>
	  			</div>
			</s:form>
		</div>
	</div>
 </div>
