<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<div class="col-md-3">
<!-- Profile Image -->
	<div class="box box-success">
    	<div class="box-body box-profile">
        	<img class="profile-user-img img-responsive img-circle" src="<s:url value="%{#contextUrl}/imagenes/administrator/email-icon.jpg"/>" alt="Imagen de perfil">
			<h3 class="profile-username text-center">Nuevo mensaje</h3>
			<p class="text-muted text-center">Seleccione un cliente para enviar mensaje</p>
			
			<s:form action="alta" namespace="/administrator/mensajes" theme="simple">
				<s:hidden name="mensaje.coach.id" value="%{#session.COACH_SESION.id}"></s:hidden>
				<s:hidden name="mensaje.tipoMensaje" value="2"></s:hidden>
				<div class="form-group">
					<label for="mailCoach"><s:text name="form.cliente"></s:text></label>
					<s:select list="listClientes" name="mensaje.cliente.id" listKey="id" listValue="%{nombre + ' ' + apellidos}" class="form-control select2 input-lg"></s:select>
				</div>
				<div class="form-group">
                	<label for="asunto"><s:text name="form.asunto"></s:text></label>
                	<s:textfield id="asunto" name="mensaje.asunto" required="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.asunto')}"></s:textfield>
                	<s:fielderror fieldName="mensaje.asunto" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                <div class="form-group">
                	<label for="cuerpo"><s:text name="form.cuerpo"></s:text></label>
                	<s:textarea rows="10" id="cuerpo" name="mensaje.mensaje" required="true" class="form-control input-lg" cssErrorClass="inputError" placeholder="%{getText('form.introduce.mensaje')}"></s:textarea>
                	<s:fielderror fieldName="mensaje.mensaje" theme="ilovefit" class="fa fa-times-circle-o"/>
                </div>
                <button type="submit" class="btn btn-primary btn-block"><b>Enviar mensaje</b></button>
			</s:form>
		</div>
        <!-- /.box-body -->
	</div>
    <!-- /.box -->
</div>
<div class="col-md-9">
	<ul class="timeline">
	<s:iterator value="mapaMensajes" var="listaMensajesFecha">
		<!-- timeline time label -->
		<li class="time-label">
			<span class="bg-light-blue-active">
            	<s:property value="key" />
			</span>
		</li>
		<s:iterator value="value" var="mensaje" status="stat">
		<li>
			<s:if test="tipoMensaje == 1 && leido==\"N\"">
				<i class="fa fa-comments bg-red"></i>
			</s:if>
			<s:else>
				<i class="fa fa-comments bg-green"></i>
			</s:else>
			<div class="timeline-item">
            	<span class="time"><i class="fa fa-clock-o"></i> <s:date name="fecha" format="dd/MM/yyyy HH:mm" /></span>
			<s:if test="%{tipoMensaje == 1}">
                <h3 class="timeline-header">
                	<a href="#"><s:property value="cliente.nombre"/> <s:property value="cliente.apellidos"/></a> te ha enviado un mensaje.
                </h3>
			</s:if>
			<s:else>
				<h3 class="timeline-header text-right">
                	<a href="#"><s:property value="coach.nombre"/></a> has enviado un mensaje a <a href="#"><s:property value="cliente.nombre"/> <s:property value="cliente.apellidos"/></a>.
                </h3>
			</s:else>
            <s:if test="tipoMensaje == 1 && leido==\"N\"">
                <div class="timeline-body">
                	<h5><b>Asunto: <s:property value="asunto"/></b></h5>
					<span class="cuerpo oculto"><s:property value="mensaje"/></span>
                </div>
                <div class="timeline-footer">
                	<sj:a class="btn btn-primary btn-xs" id="leerMensaje%{id}" href="administrator/mensajes/leer?mensaje.id=%{id}" targets="divLeerMensaje%{id}" onCompleteTopics="leerMensaje">
					  	Leer mensaje
					</sj:a>
					<div id="divLeerMensaje<s:property value="id" />"></div>
                </div>
			</s:if>
			<s:else>
				<div class="timeline-body">
                	<h5><b>Asunto: <s:property value="asunto"/></b></h5>
					<span><s:property value="mensaje"/></span>
                </div>
			</s:else>
			<s:iterator value="#mensaje.mensajeses" var="mensajeHijo" status="statHijo">
                <ul class="timeline">
					<li>
					<s:if test="tipoMensaje == 1 && leido==\"N\"">
						<i class="fa fa-comments bg-red"></i>
					</s:if>
					<s:else>
						<i class="fa fa-comments bg-green"></i>
					</s:else>
						<div class="timeline-item">
							<span class="time"><i class="fa fa-clock-o"></i> <s:date name="fecha" format="dd/MM/yyyy HH:mm" /></span>
							<s:if test="%{tipoMensaje == 1}">
				                <h3 class="timeline-header">
				                	<a href="#"><s:property value="cliente.nombre"/> <s:property value="cliente.apellidos"/></a> te ha enviado un mensaje.
				                </h3>
							</s:if>
							<s:else>
								<h3 class="timeline-header text-right">
				                	<a href="#"><s:property value="coach.nombre"/></a>.
				                </h3>
							</s:else>
			                
		                <s:if test="tipoMensaje == 1 && leido==\"N\"">
			                <div class="timeline-body">
			                	<h5><b>Asunto: <s:property value="asunto"/></b></h5>
								<span class="cuerpo oculto"><s:property value="mensaje"/></span>
			                </div>
			                <div class="timeline-footer">
			                	<sj:a class="btn btn-primary btn-xs" id="leerMensaje%{id}" href="administrator/mensajes/leer?mensaje.id=%{id}" targets="divLeerMensaje%{id}" onCompleteTopics="leerMensaje">
								  	Leer mensaje
								</sj:a>
								<div id="divLeerMensaje<s:property value="id" />"></div>
			                </div>
						</s:if>
						<s:else>
							<div class="timeline-body">
			                	<h5><b>Asunto: <s:property value="asunto"/></b></h5>
								<span><s:property value="mensaje"/></span>
			                </div>
						</s:else>
						</div>
					</li>
				</ul>
			</s:iterator>
				<div class="box-footer">
   					<s:form action="alta" namespace="/administrator/mensajes" theme="simple">
   						<div class="input-group">
      						<s:hidden name="mensaje.coach.id" value="%{#session.COACH_SESION.id}"></s:hidden>
							<s:hidden name="mensaje.tipoMensaje" value="2"></s:hidden>
							<s:hidden name="mensaje.cliente.id" value="%{cliente.id}"></s:hidden>
							<s:hidden name="mensaje.asunto" value="%{asunto}"></s:hidden>
							<s:hidden name="mensaje.mensajes.id" value="%{id}"></s:hidden>
							<s:textfield class="form-control" placeholder="Escribe un mensaje..." name="mensaje.mensaje"></s:textfield>
     						<div class="input-group-btn">
       							<button type="submit" class="btn btn-success"><i class="fa fa-plus"></i></button>
     						</div>
   						</div>
   					</s:form>
 				</div>
			</div>
		</li>
		</s:iterator>
	</s:iterator>
		<li>
			<i class="fa fa-clock-o bg-gray"></i>
		</li>
	</ul>
	<script type="text/javascript">
		$.subscribe('leerMensaje', function(event,data) {
			var mensaje = $(data).parent().parent().parent();
			$("span.cuerpo", mensaje).removeClass("oculto");
			$("i.fa-comments", mensaje).removeClass("bg-red").addClass("bg-green");
			$("div.timeline-footer", mensaje).remove();
		});
	
		function leerMensaje (item) {
			alert($("span.cuerpo", item).attr("class"));
			$("span.cuerpo", item).removeClass("oculto");
		}
	</script>
</div>