package minh.lehong.yourwindowyoursoul.constant.enums;

public enum ExceptionEnums {

	EXCEPTION_400("exception-400", 400),

	EXCEPTION_404("exception_404", 404),

	EXCEPTION_405("exception_405", 405),

	EXCEPTION_406("exception_406", 406),

	EXCEPTION_409("exception_409", 409);

	private final String desc;
	private final int code;

	ExceptionEnums(final String desc, final int code) {
		this.desc = desc;
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return desc;
	}
}
