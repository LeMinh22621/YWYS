package minh.lehong.yourwindowyoursoul.payload.request;

import lombok.Data;

import java.io.Serializable;


@Data
public class EPUserRequest implements Serializable {

	private static final long serialVersionUID = -5259030773336340804L;

	private String password;

	private String name;

	private String email;
}
