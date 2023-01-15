package ar.com.codoacodo.dao;
import ar.com.codoacodo.domain.Departamento;
import java.util.List;

public interface iDepartamentoDAO {
	
	
	
	public Departamento getById(Long id) throws Exception; 
	
	public List<Departamento> findAll() throws Exception;	
	
	public void delete(Long id) throws Exception;
	
	public void update(Departamento depto) throws Exception; 
	
	public void create(Departamento newDepto) throws Exception;
	
    public List<Departamento> search(String clave) throws Exception;
	
	
}
