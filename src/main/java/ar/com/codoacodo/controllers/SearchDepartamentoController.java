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

@WebServlet("/SearchDepartamentoController")
public class SearchDepartamentoController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		 iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		
		String clave = req.getParameter("claveBusqueda");
		
		List<Departamento> depto;
		try {
			depto = dao.search(clave);
		} catch (Exception e) {
			depto = List.of();
			e.printStackTrace();
		}
		
		req.setAttribute("listado", depto);
		
		getServletContext().getRequestDispatcher("/listado.jsp").forward(req, resp);
	}
}