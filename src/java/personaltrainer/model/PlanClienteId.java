package personaltrainer.model;
// Generated 14-ago-2017 23:50:33 by Hibernate Tools 5.2.3.Final

import java.util.Date;

/**
 * PlanClienteId generated by hbm2java
 */
public class PlanClienteId implements java.io.Serializable {

	private int idPlan;
	private int idCliente;
	private Date fechaInicio;
	private String realizado;

	public PlanClienteId() {
	}

	public PlanClienteId(int idPlan, int idCliente, String realizado) {
		this.idPlan = idPlan;
		this.idCliente = idCliente;
		this.realizado = realizado;
	}

	public PlanClienteId(int idPlan, int idCliente, Date fechaInicio, String realizado) {
		this.idPlan = idPlan;
		this.idCliente = idCliente;
		this.fechaInicio = fechaInicio;
		this.realizado = realizado;
	}

	public int getIdPlan() {
		return this.idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getRealizado() {
		return this.realizado;
	}

	public void setRealizado(String realizado) {
		this.realizado = realizado;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof PlanClienteId))
			return false;
		PlanClienteId castOther = (PlanClienteId) other;

		return (this.getIdPlan() == castOther.getIdPlan()) && (this.getIdCliente() == castOther.getIdCliente())
				&& ((this.getFechaInicio() == castOther.getFechaInicio())
						|| (this.getFechaInicio() != null && castOther.getFechaInicio() != null
								&& this.getFechaInicio().equals(castOther.getFechaInicio())))
				&& ((this.getRealizado() == castOther.getRealizado()) || (this.getRealizado() != null
						&& castOther.getRealizado() != null && this.getRealizado().equals(castOther.getRealizado())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getIdPlan();
		result = 37 * result + this.getIdCliente();
		result = 37 * result + (getFechaInicio() == null ? 0 : this.getFechaInicio().hashCode());
		result = 37 * result + (getRealizado() == null ? 0 : this.getRealizado().hashCode());
		return result;
	}

}