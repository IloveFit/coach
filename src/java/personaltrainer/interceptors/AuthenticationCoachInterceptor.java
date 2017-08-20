package personaltrainer.interceptors;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import personaltrainer.model.Coach;
import personaltrainer.util.Constantes;

public class AuthenticationCoachInterceptor implements Interceptor {

	private static final long serialVersionUID = 1068538915373540051L;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Map<String, Object> sessionAttributes = actionInvocation.getInvocationContext().getSession();
		
		Coach coach = (Coach) sessionAttributes.get(Constantes.COACH_SESION);
		
		if (coach != null) {
			return actionInvocation.invoke();
		} else {
			return Action.LOGIN;
		}
		
	}

}
