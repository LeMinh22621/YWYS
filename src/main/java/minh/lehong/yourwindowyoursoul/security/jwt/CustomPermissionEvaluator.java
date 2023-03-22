//package minh.lehong.yourwindowyoursoul.security.jwt;
//
//import org.springframework.security.access.PermissionEvaluator;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//
//
//@Component
//public class CustomPermissionEvaluator implements PermissionEvaluator {
//
//	@Override
//	public boolean hasPermission(final Authentication authentication, final Object accessType, final Object permission) {
//		if (authentication != null && accessType instanceof String) {
//			if ("hasAccess".equalsIgnoreCase(String.valueOf(accessType))) {
//				final boolean hasAccess = validateAccess(String.valueOf(permission));
//				return hasAccess;
//			}
//			return false;
//		}
//		return false;
//	}
//
//	private boolean validateAccess(final String permission) {
//		// ideally should be checked with user role, permission in database
//		if ("READ".equalsIgnoreCase(permission)) {
//			return true;
//		}
//		return false;
//	}
//
//	@Override
//	public boolean hasPermission(final Authentication authentication, final Serializable serializable, final String targetType, final Object permission) {
//		return false;
//	}
//
//}
