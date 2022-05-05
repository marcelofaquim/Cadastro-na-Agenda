package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAO;
import model.JavaBeans;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/Controller", "/main", "/inserir","/select","/update","/delete"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	DAO dao = new DAO();
	JavaBeans clientes = new JavaBeans();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controller() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String action = request.getServletPath();
		System.out.println(action);
		if(action.equals("/main")) {
			clientes(request, response);
		}else
			if(action.equals("/inserir")) {
				novoCliente(request, response);
			}else if(action.equals("/select")){
				listarContato(request,response);
			}else if(action.equals("/update")) {
				editarContato(request,response);
				
			}else if(action.equals("/delete")) {
				removerClientes(request, response);
			}else
				response.sendRedirect("index.html");
	}

	protected void clientes (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("cliente.jsp");
		
ArrayList<JavaBeans> lista = dao.listaClientes();
		
		
		request.setAttribute("clientes", lista);
		RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
		rd.forward(request, response);
	}
	
	protected void novoCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.clientes.setNome(request.getParameter("nome"));
		this.clientes.setFone(request.getParameter("fone"));
		this.clientes.setEmail(request.getParameter("email"));
		this.clientes.setEndereco(request.getParameter("endereco"));
		this.clientes.setBairro(request.getParameter("bairro"));
		this.clientes.setObs(request.getParameter("obsPath"));
		
dao.inserirClientes(clientes);
		
		response.sendRedirect("index.html");
	}
		
		protected void listarContato(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
				//Receber o parametro
				String idcon = request.getParameter("idcon");
				//Testar envio do parametro
				System.out.println(idcon);
				//configurar o paramentro do objetivo
				this.clientes.setIdcon(idcon);
				//selecionar o contato
				this.dao.selecionarClientes(clientes);
				
				request.setAttribute("idcon", clientes.getIdcon());	
				request.setAttribute("Nome", clientes.getNome());
				request.setAttribute("Fone", clientes.getFone());
				request.setAttribute("Email", clientes.getEmail());
				
				RequestDispatcher rd= request.getRequestDispatcher("editar.jsp");
				rd.forward(request, response);
		}
		
		protected void editarContato(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException{
				
				this.clientes.setNome(request.getParameter("nome"));
				this.clientes.setFone(request.getParameter("fone"));
				this.clientes.setEmail(request.getParameter("email"));
				this.dao.alterarClientes(this.clientes);
				response.sendRedirect("main");
		

	}
		
		protected void removerClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
			String idcon = request.getParameter("idcon");
			clientes.setIdcon(idcon);
			this.dao.removerClientes(this.clientes);
			response.sendRedirect("main");
		}
	
	
}
