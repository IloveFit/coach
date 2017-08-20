package personaltrainer.dao;

import java.util.List;

import personaltrainer.model.Catalogo;

public interface CatalogoDao {

	Catalogo altaCatalogo (Catalogo catalogo);
	
	void eliminarCatalogo (Integer id);
	
	Catalogo modificarCatalogo (Catalogo catalogo);
	
	Catalogo getCatalogo (Integer id);
	
	List<Catalogo> listarCatalogo ();
	
	List<Catalogo> listarCatalogo (String tipoCatalogo);
	
}