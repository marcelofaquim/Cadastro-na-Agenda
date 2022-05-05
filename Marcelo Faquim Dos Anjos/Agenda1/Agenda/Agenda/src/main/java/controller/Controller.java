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
@WebServlet(urlPatterns = {"/Controller","/main","/inserir","/select","/update","/delete"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	DAO dao = new DAO();
	JavaBeans contato = new JavaBeans();
       
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
		//teste conexão
		//DAO dao = new DAO();
		//dao.testeConexao();
		
		String action = request.getServletPath();
		System.out.println(action);
		if(action.equals("/main")) {
			contatos(request, response);
		}else
			if (action.equals("/inserir")) {
				novoContato(request,response);
			}else if(action.equals("/select")){
				listarContato(request,response);
			}else if(action.equals("/update")) {
				editarContato(request,response);
				
			}else if(action.equals("/delete")) {
				removerContato(request, response);
			} else
				response.sendRedirect("index.html");
	}
	
	
	

	protected void contatos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.sendRedirect("agenda.jsp");
		ArrayList<JavaBeans> lista = dao.listaContato();
		
		
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}
	
	protected void novoContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getParameter("nome"));
		//System.out.println(request.getParameter("fone"));
		//System.out.println(request.getParameter("email"));
		
		this.contato.setNome(request.getParameter("nome"));
		this.contato.setFone(request.getParameter("fone"));
		this.contato.setEmail(request.getParameter("email"));
		this.contato.setObs(request.getParameter("obs"));
		
		dao.inserirContato(contato);
		
		response.sendRedirect("main");
	}
	
	//Listar COntatos
		protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			//Receber o parametro
			String idcon = request.getParameter("idcon");
			//Testar envio do parametro
			System.out.println(idcon);
			//configurar o paramentro do objetivo
			this.contato.setIdcon(idcon);
			//selecionar o contato
			this.dao.selecionarContato(contato);
			// teste
			/*System.out.println(contato.getIdcon());
			 System.out.println(contato.getNome());
			 System.out.println(contato.getFone());
			 System.out.println(contato.getEmail());* 
			 */
			request.setAttribute("idcon", contato.getIdcon());	
			request.setAttribute("Nome", contato.getNome());
			request.setAttribute("Fone", contato.getFone());
			request.setAttribute("Email", contato.getEmail());
			RequestDispatcher rd= request.getRequestDispatcher("editar.jsp");
			rd.forward(request, response);
		}
		
		protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			
			this.contato.setNome(request.getParameter("nome"));
			this.contato.setFone(request.getParameter("fone"));
			this.contato.setEmail(request.getParameter("email"));
			this.dao.alterarContato(this.contato);
			response.sendRedirect("main");
			
			//Receber dados da instancia JavaBeans
		//ArrayList<JavaBeans> lista = dao.listarContato();
			
		}
		
protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
			
			String idcon = request.getParameter("idcon");
			contato.setIdcon(idcon);
			this.dao.removerContato(this.contato);
			response.sendRedirect("main");
		}
}
