package minh.lehong.yourwindowyoursoul.configuration;

import minh.lehong.yourwindowyoursoul.exceptions.ServiceException;
import minh.lehong.yourwindowyoursoul.security.jwt.service.JwtTokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class Interceptor implements HandlerInterceptor {
	private final JwtTokenService jwtService;

	public Interceptor(final JwtTokenService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {

		final String token = request.getHeader("Authorization");

		request.setAttribute("Authorization", token);

		final String url = request.getRequestURL().toString();

		if (url.contains("/login")) {
			return true;
		}
		if (StringUtils.isEmpty(token)) {
			throw new ServiceException("please login for this content", HttpStatus.UNAUTHORIZED);
		}
		if (!jwtService.validateTokenLogin(token)) {
			throw new ServiceException("token expired", HttpStatus.UNAUTHORIZED);
		}
		return true;
	}

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
	}
}