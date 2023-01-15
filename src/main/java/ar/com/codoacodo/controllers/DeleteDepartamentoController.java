package ar.com.codoacodo.controllers;
import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteDepartamentoController")
public class DeleteDepartamentoController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long id = Long.parseLong(req.getParameter("idDepto"));

		iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
		
		try {
			dao.delete(id);
		
			req.setAttribute("success", List.of("Se he eliminado el producto con id:" + id));
		} catch (Exception e) {
			e.printStackTrace();
		
			req.setAttribute("erorrs", List.of("NO se he eliminado el producto :" + e.getMessage()));
		}
		
		getServletContext().getRequestDispatcher("/FindAllDepartamentoController").forward(req, resp);
		
	}

}
