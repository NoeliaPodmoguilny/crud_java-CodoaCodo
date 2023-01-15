package ar.com.codoacodo.controllers;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.domain.Departamento;

@WebServlet("/UpdateDepartamentoController")
public class UpdateDepartamentoController extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nombre= req.getParameter("nombre");
		String id = req.getParameter("id");
		String presupuesto = req.getParameter("presupuesto");
		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		
		Departamento d;
       
		d = new Departamento(Long.parseLong(id),nombre,Double.parseDouble(presupuesto));
		
		try { 
			dao.update(d);
		
			req.setAttribute("success", List.of("Departamento id:" + d.getId() + " actualizado correctamente"));
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("errors", List.of("Error actualizando Departamento<" + e.getMessage()));
		}
		
	     getServletContext().getRequestDispatcher("/FindAllDepartamentoController").forward(req, resp);
		
	}
	

		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String id = req.getParameter("id");
			
			
			iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
			
			Departamento d = null;

			try {
				d = dao.getById(Long.parseLong(id));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			req.setAttribute("departamento", d);
	
		     getServletContext().getRequestDispatcher("/editar.jsp").forward(req, resp);
		}

}
