package modelo;

import java.util.Objects;

public class UserRequest {
	User user;
	Reference reference;

	public UserRequest(User user, Reference reference) {
		super();
		this.user = user;
		this.reference = reference;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
	}

	@Override
	public int hashCode() {
		return Objects.hash(reference, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRequest other = (UserRequest) obj;
		return Objects.equals(reference, other.reference) && Objects.equals(user, other.user);
	}

}
