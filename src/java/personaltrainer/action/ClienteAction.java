package personaltrainer.action;

import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.hibernate.SessionFactory;

import com.miguelangeljulvez.easyredsys.client.AppConfig;
import com.miguelangeljulvez.easyredsys.client.core.MessageOrderCESRequest;
import com.miguelangeljulvez.easyredsys.client.core.OrderCES;
import com.miguelangeljulvez.easyredsys.client.util.Currency;
import com.miguelangeljulvez.easyredsys.client.util.Language;
import com.miguelangeljulvez.easyredsys.client.util.PaymentMethod;
import com.miguelangeljulvez.easyredsys.client.util.TransactionType;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import personaltrainer.dao.ClienteDao;
import personaltrainer.dao.ClienteDaoImpl;
import personaltrainer.model.Cliente;
import personaltrainer.redsys.AppConfigImpl;
import personaltrainer.util.Constantes;

public class ClienteAction extends ActionSupport implements Action, ModelDriven<Cliente>, ServletContextAware, ServletRequestAware {

	private static final long serialVersionUID = 3983280865905617692L;
	
	private ServletContext ctx;
	private HttpServletRequest servletRequest;
	
	private Cliente cliente;
	
	private String repetirPass;
	
	//pasarela de pago
	private MessageOrderCESRequest messageOrderCESRequest;
	
	
	public String registro() throws Exception {
		SessionFactory sf = (SessionFactory) ctx.getAttribute("SessionFactory");
		ClienteDao clienteDao = new ClienteDaoImpl(sf);
		
		try {
			Cliente altaCliente = getModel();
			//baja N
			altaCliente.setBaja(Constantes.NO);
			//activo N
			altaCliente.setActivo(Constantes.NO);
			//fecha de alta
			altaCliente.setFechaRegistro(new Date());
			
			if(clienteDao.altaCliente(altaCliente) != null) {
				//preparamos los datos para pagar
				OrderCES orderCES = new OrderCES.Builder(AppConfigImpl.class)
                        .transactionType(TransactionType.AUTORIZACION)
                        .currency(Currency.EUR)
                        .consumerLanguage(Language.SPANISH)
                        .order(getRandomOrder()) //Identificador que debes generar con 12 caracteres máximo y único
                        .amount(3000) //La cantidad es sin decimales. Por ejemplo, 10.00€ correspondería al valor 1000
                        .productDescription("Product description")
                        .payMethods(PaymentMethod.TARJETA)
                        .urlOk("https://easyredsys.miguelangeljulvez.com/easyredsys/response-ces-ok.jsp")
                        .urlKo("https://easyredsys.miguelangeljulvez.com/easyredsys/response-ces-error.jsp")
                        .urlNotification("http://easyredsys.miguelangeljulvez.com/easyredsys/rest/InotificacionSIS") //Ver sección notificaciones mas adelante
                        .build();

MessageOrderCESRequest messageOrderCESRequest = new MessageOrderCESRequest.Builder(AppConfigImpl.class)
                        .withOrder(orderCES)
                        .build();
			setMessageOrderCESRequest(messageOrderCESRequest);

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(getText("cliente.alta.ko"));
			
			return ERROR;
		} 
		
		return SUCCESS;
	}
	
	public void validateRegistro() {
		if (StringUtils.isEmpty(getModel().getNombre())) {
			addFieldError("cliente.nombre", getText("campo.obligatorio", "Nombre"));
		}
		if (StringUtils.isEmpty(getModel().getApellidos())) {
			addFieldError("cliente.apellidos", getText("campo.obligatorio", "Apellidos"));
		}
		if (StringUtils.isEmpty(getModel().getEmail())) {
			addFieldError("cliente.email", getText("campo.obligatorio", "email"));
		}
		if (StringUtils.isEmpty(getModel().getPass())) {
			addFieldError("cliente.pass", getText("campo.obligatorio", "Password"));
		} else if (getModel().getPass().length() < 4 || getModel().getPass().length() > 12) {
			addFieldError("cliente.pass", getText("coach.pass.length"));
		}
		if (StringUtils.isEmpty(getRepetirPass())) {
			addFieldError("repetirPass", getText("campo.obligatorio", "Password"));
		} else if (getRepetirPass().length() < 4 || getRepetirPass().length() > 12) {
			addFieldError("repetirPass", getText("coach.pass.length"));
		}
		
		if (!getModel().getPass().equals(getRepetirPass())) {
			addFieldError("repetirPass", getText("coach.modificar.pass.iguales"));
		}
	}
	
	/**
	 * Generar numeros de pedido aleatorios
	 * @return
	 */
	private String getRandomOrder() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	    StringBuilder sb = new StringBuilder();
	    Random random = new Random();
	    for (int i = 0; i < 12; i++) {
	        char c = chars[random.nextInt(chars.length)];
	        sb.append(c);
	    }
	    return sb.toString();
	}

	@Override
	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.ctx = context;
		
	}

	@Override
	public Cliente getModel() {
		return cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getRepetirPass() {
		return repetirPass;
	}

	public void setRepetirPass(String repetirPass) {
		this.repetirPass = repetirPass;
	}

	public MessageOrderCESRequest getMessageOrderCESRequest() {
		return messageOrderCESRequest;
	}

	public void setMessageOrderCESRequest(MessageOrderCESRequest messageOrderCESRequest) {
		this.messageOrderCESRequest = messageOrderCESRequest;
	}
	
}