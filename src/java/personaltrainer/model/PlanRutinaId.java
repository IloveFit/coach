package personaltrainer.model;
// Generated 01-ago-2017 20:54:01 by Hibernate Tools 5.2.3.Final

/**
 * PlanRutinaId generated by hbm2java
 */
public class PlanRutinaId implements java.io.Serializable {

	private int idPlan;
	private int idRutina;

	public PlanRutinaId() {
	}

	public PlanRutinaId(int idPlan, int idRutina) {
		this.idPlan = idPlan;
		this.idRutina = idRutina;
	}

	public int getIdPlan() {
		return this.idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public int getIdRutina() {
		return this.idRutina;
	}

	public void setIdRutina(int idRutina) {
		this.idRutina = idRutina;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PlanRutinaId))
			return false;
		PlanRutinaId castOther = (PlanRutinaId) other;

		return (this.getIdPlan() == castOther.getIdPlan()) && (this.getIdRutina() == castOther.getIdRutina());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdPlan();
		result = 37 * result + this.getIdRutina();
		return result;
	}

}