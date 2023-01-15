package ar.com.codoacodo.controllers;
import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.domain.Departamento;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/CreateDepartamentoController")
public class CreateDepartamentoController extends HttpServlet {


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nombre= req.getParameter("nombre");
		String id = req.getParameter("id");
		String presupuesto = req.getParameter("presupuesto");
		
		
		List<String> errores = new ArrayList<>();
		if(nombre == null || "".equals(nombre)) {
			errores.add("Nombre vacío");
		}
		if(id== null || "".equals(id)) {
			errores.add("Id vacío");
		}
		if(presupuesto== null || "".equals(presupuesto)) {
			errores.add("Presupuesto vacío");
		}
	
		if(!errores.isEmpty()) {
			req.setAttribute("errors", errores);
		
			getServletContext().getRequestDispatcher("/nuevo.jsp").forward(req, resp);
			return;
		}

		
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		
		Departamento d;
       
		d = new Departamento(Long.parseLong(id),nombre,Double.parseDouble(presupuesto));

		try {
			 dao.create(d);
			 req.setAttribute("success", List.of("Alta de producto exitosa"));
		}catch (Exception e) {
		
			e.printStackTrace();
			
		 }
		getServletContext().getRequestDispatcher("/FindAllDepartamentoController").forward(req, resp);
		
		
	}

}
