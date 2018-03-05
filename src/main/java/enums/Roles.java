package enums;

public enum Roles {
	USER, MODERATOR, ADMINISTRATOR;

	@Override
	public String toString() {
		return this.name();
	}
}
