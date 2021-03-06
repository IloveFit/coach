package personaltrainer.model;
// Generated 14-ago-2017 23:50:33 by Hibernate Tools 5.2.3.Final

/**
 * ClienteCoachId generated by hbm2java
 */
public class ClienteCoachId implements java.io.Serializable {

	private int idCliente;
	private int idCoach;

	public ClienteCoachId() {
	}

	public ClienteCoachId(int idCliente, int idCoach) {
		this.idCliente = idCliente;
		this.idCoach = idCoach;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdCoach() {
		return this.idCoach;
	}

	public void setIdCoach(int idCoach) {
		this.idCoach = idCoach;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ClienteCoachId))
			return false;
		ClienteCoachId castOther = (ClienteCoachId) other;

		return (this.getIdCliente() == castOther.getIdCliente()) && (this.getIdCoach() == castOther.getIdCoach());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdCliente();
		result = 37 * result + this.getIdCoach();
		return result;
	}

}
