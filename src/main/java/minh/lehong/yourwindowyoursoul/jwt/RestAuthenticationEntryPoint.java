package minh.lehong.yourwindowyoursoul.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException)
			throws IOException {
		//		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getWriter().write("Unauthorized");
	}
}
