package ar.com.codoacodo.controllers;

import ar.com.codoacodo.dao.iDepartamentoDAO;
import ar.com.codoacodo.dao.implement.DepartamentoDAOMysqlImpl;
import ar.com.codoacodo.domain.Departamento;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/FindAllDepartamentoController")

public class FindAllDepartamentoController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	     iDepartamentoDAO dao = new DepartamentoDAOMysqlImpl();
	     List<Departamento> departamentos = new ArrayList<>();
			
	       try {
	    	 departamentos = dao.findAll();
	       } catch (Exception e) {
	    	
	    	 e.printStackTrace(); 
	       }
	       req.setAttribute("listado", departamentos);
	    
	       getServletContext().getRequestDispatcher("/listado.jsp").forward(req, resp);
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
