package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.domain.Departamento;

public class GetDepartamentoController  {
	
	public static void main(String[] args) {
		
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		
		Long id = 11l;
		Departamento d;

		try {
		d = dao.getById(id);
		}
		catch(Exception e) {
			d= null;
		}
		if (d != null) {
			System.out.println(d);
		}else {
			System.err.println("No existe el id:"+ id);
		}
		
	}

}
