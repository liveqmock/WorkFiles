package cn.com.kxcomm.base.interceptor;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import cn.com.kxcomm.selfservice.action.util.AuthenticationUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * 功能描述:验证用户登录拦截器
 * <p>
 * 版权所有：康讯通讯
 * <p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 * 
 * @author chenliang 新增日期：2013-07-14
 * @author 你的姓名 修改日期：2008-11-14
 * @since wapportal_manager version(2.0)
 */

@Controller
public class AuthenticationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = -4994877859122219609L;
	
	final static public String TIMEOUT = "timeout";
	
	private static Logger _log = Logger.getLogger(AuthenticationInterceptor.class);


	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext actionContext = invocation.getInvocationContext();
		_log.info("---AuthenticationInterceptor----" + invocation.getAction()
				+ "!" + invocation.getResultCode());
		Map session = actionContext.getSession();
//		if (AuthenticationUtil.isSessionActivated(session)) {
			return invocation.invoke();
//		}	
//		return TIMEOUT;
	}

}
