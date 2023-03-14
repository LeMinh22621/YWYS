package minh.lehong.yourwindowyoursoul.security.jwt;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UserAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(final HttpServletRequest request, final HttpServletResponse response, final AccessDeniedException exc)
			throws IOException, ServletException {
		//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getWriter().write("Access Denied!");

	}
}