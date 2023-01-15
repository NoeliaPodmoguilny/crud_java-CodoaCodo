package ar.com.codoacodo.dao.implement;
import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.db.AdministradorDeConexiones;
import ar.com.codoacodo.domain.Departamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class DepartamentoDAOMysqlImpl implements iDepartamentoDAO {

	@Override
	public Departamento getById(Long id)  throws Exception{
		
		Connection connection = AdministradorDeConexiones.getConnection();
		
		String sql = "select * from departamentos where id = " + id;
	   
		Statement statement  = connection.createStatement();
		
		
		ResultSet resultset = statement.executeQuery(sql);
		
		if (resultset.next()){
			
			Long idBd = resultset.getLong("id");
			String nombreBd = resultset.getString("nombre");
			Double presupuestoBd = resultset.getDouble("presupuesto");
			return new Departamento(idBd,nombreBd,presupuestoBd);
			
		}
		cerrar(connection);
		return null; 
	}

	@Override
	public List<Departamento> findAll() throws Exception {
	
		Connection connection = AdministradorDeConexiones.getConnection();
	
	    String sql = "select * from departamentos";
			   
		Statement statement  = connection.createStatement();

		ResultSet resultset = statement.executeQuery(sql);

		List<Departamento> departamentos = new ArrayList<Departamento>();	
			
	
			while (resultset.next()){
			
			Long idBd = resultset.getLong("id");
			String nombreBd = resultset.getString("nombre");
			Double presupuestoBd = resultset.getDouble("presupuesto");
			Departamento d = new Departamento(idBd,nombreBd,presupuestoBd);
			departamentos.add(d);
					
			}
			cerrar(connection);
	
		   return departamentos; 
	}

	@Override
	public void delete(Long id) throws Exception {
	
		Connection connection = AdministradorDeConexiones.getConnection();
	
	     String sql = "DELETE FROM departamentos WHERE ID=" + id;
	 	 Statement statement  = connection.createStatement();
	 	
		 statement.executeUpdate(sql);
		  cerrar(connection);
		 
	}

	@Override
	public void update(Departamento depto) throws Exception {
		
		Connection connection = AdministradorDeConexiones.getConnection();
		
		 String sql = "update departamentos set nombre = ?, presupuesto = ? where id= ?"  ;
		 PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	     statement.setString(1,depto.getNombre());
		 statement.setDouble(2,depto.getPresupuesto());
		 statement.setLong(3,depto.getId());
		 statement.execute();
		
		 cerrar(connection);
	}

	@Override
	public void create(Departamento newDepto) throws Exception {
		
		Connection connection = AdministradorDeConexiones.getConnection();

		 String sql = "insert into departamentos (id, nombre, presupuesto) values (?,?,?)" ;
		 PreparedStatement statement  = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		 statement.setLong(1,newDepto.getId());
		 statement.setString(2,newDepto.getNombre());
		 statement.setDouble(3,newDepto.getPresupuesto());
		 statement.execute();
		 
		 ResultSet res = statement.getGeneratedKeys(); 
		 if (res.next()) {
			 System.out.println("Se creo el departamento correctamente");
		 }
		 cerrar(connection);
	}
	@Override
	public List<Departamento> search(String clave) throws Exception {
	
		Connection connection = AdministradorDeConexiones.getConnection();

		String sql = "SELECT * FROM DEPARTAMENTOS WHERE NOMBRE LIKE ?";
		PreparedStatement statement = connection.prepareStatement(sql);

		statement.setString(1, "%" + clave + "%");
		
		ResultSet resultSet = statement.executeQuery();

		List<Departamento> depto = new ArrayList<Departamento>();

		while (resultSet.next()) {
			depto.add(this.crearDepto(resultSet));
		}
		
		cerrar(connection);
		
		return depto;
	}
	
	private void cerrar(Connection con) throws Exception{
		con.close();
	}
	
	private Departamento crearDepto(ResultSet resultSet) throws Exception {

		Long idDb = resultSet.getLong("id");
		String nombre = resultSet.getString("nombre");
		Double presupuesto = resultSet.getDouble("presupuesto");
		

		return new Departamento(idDb, nombre, presupuesto);
	}
	
	// implementar el nuevo metodo que busca por el nombre y que devuelve una lista de departamentos

}
