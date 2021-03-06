package personaltrainer.model;
// Generated 14-ago-2017 23:50:33 by Hibernate Tools 5.2.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Plan generated by hbm2java
 */
public class Plan implements java.io.Serializable {

	private Integer id;
	private Catalogo catalogoByNivel;
	private Catalogo catalogoByMaquina;
	private Catalogo catalogoByObjetivo;
	private String nombre;
	private String descripcion;
	private int duracion;
	private String activo;
	private Set<PlanRutina> planRutinas = new HashSet<PlanRutina>(0);
	private Set<PlanCliente> planClientes = new HashSet<PlanCliente>(0);

	public Plan() {
	}

	public Plan(Catalogo catalogoByNivel, Catalogo catalogoByMaquina, Catalogo catalogoByObjetivo, String nombre,
			int duracion, String activo) {
		this.catalogoByNivel = catalogoByNivel;
		this.catalogoByMaquina = catalogoByMaquina;
		this.catalogoByObjetivo = catalogoByObjetivo;
		this.nombre = nombre;
		this.duracion = duracion;
		this.activo = activo;
	}

	public Plan(Catalogo catalogoByNivel, Catalogo catalogoByMaquina, Catalogo catalogoByObjetivo, String nombre,
			String descripcion, int duracion, String activo, Set<PlanRutina> planRutinas,
			Set<PlanCliente> planClientes) {
		this.catalogoByNivel = catalogoByNivel;
		this.catalogoByMaquina = catalogoByMaquina;
		this.catalogoByObjetivo = catalogoByObjetivo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracion = duracion;
		this.activo = activo;
		this.planRutinas = planRutinas;
		this.planClientes = planClientes;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Catalogo getCatalogoByNivel() {
		return this.catalogoByNivel;
	}

	public void setCatalogoByNivel(Catalogo catalogoByNivel) {
		this.catalogoByNivel = catalogoByNivel;
	}

	public Catalogo getCatalogoByMaquina() {
		return this.catalogoByMaquina;
	}

	public void setCatalogoByMaquina(Catalogo catalogoByMaquina) {
		this.catalogoByMaquina = catalogoByMaquina;
	}

	public Catalogo getCatalogoByObjetivo() {
		return this.catalogoByObjetivo;
	}

	public void setCatalogoByObjetivo(Catalogo catalogoByObjetivo) {
		this.catalogoByObjetivo = catalogoByObjetivo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getDuracion() {
		return this.duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getActivo() {
		return this.activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public Set<PlanRutina> getPlanRutinas() {
		return this.planRutinas;
	}

	public void setPlanRutinas(Set<PlanRutina> planRutinas) {
		this.planRutinas = planRutinas;
	}

	public Set<PlanCliente> getPlanClientes() {
		return this.planClientes;
	}

	public void setPlanClientes(Set<PlanCliente> planClientes) {
		this.planClientes = planClientes;
	}

}
