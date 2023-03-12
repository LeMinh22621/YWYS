
package minh.lehong.yourwindowyoursoul.config;

import minh.lehong.yourwindowyoursoul.jwt.service.JwtTokenService;
import minh.lehong.yourwindowyoursoul.enterprise.ep.utils.EPAccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{

	private static final String TOKEN_HEADER = "Authorization";

	@Autowired
	private JwtTokenService jwtTokenService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws
			ServletException, IOException
	{
		String authToken = httpServletRequest.getHeader(TOKEN_HEADER);
		UserDetails userDetails = null;
		String custId = EPAccessTokenUtil.getEPUserIdToken(authToken);
		UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(userDetails,
				custId, null);
		authen.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
		SecurityContextHolder.getContext().setAuthentication(authen);

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

}
