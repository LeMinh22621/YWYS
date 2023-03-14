package minh.lehong.yourwindowyoursoul.configuration;

import minh.lehong.yourwindowyoursoul.security.jwt.service.JwtTokenService;
import minh.lehong.yourwindowyoursoul.utils.EPAccessTokenUtil;
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

//	@Autowired
//	private JwtTokenService jwtService;

	@Override
	protected void doFilterInternal(
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws
			ServletException, IOException
	{
		String authToken = httpServletRequest.getHeader(TOKEN_HEADER);
		UserDetails userDetails = null;
		String userIdToken = EPAccessTokenUtil.getEPUserIdToken(authToken);
		UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(userDetails,
				userIdToken, null);
		authen.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
		SecurityContextHolder.getContext().setAuthentication(authen);

		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

//	@Override
//	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//		String path = request.getServletPath();
//		// Add conditions to exclude certain routes from being filtered
//		return path.startsWith("/signup");
//	}

}
