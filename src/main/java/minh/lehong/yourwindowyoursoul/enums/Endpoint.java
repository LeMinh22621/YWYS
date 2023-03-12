package minh.lehong.yourwindowyoursoul.enums;

public enum Endpoint {
	ROOT("/"),
	LOGIN("/login");

	private final String path;

	Endpoint(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}
}
