package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {

	private String driver="com.mysql.cj.jdbc.Driver";
	private String url="jdbc:mysql://127.0.0.1:3306/dbclientes?useTimezone=true&serverTimzeone=UTC";
	private String user="root";
	private String password="";

	//Conexão
	private Connection conectar() {
	Connection con = null;
	try {
	Class.forName(driver);
	con = DriverManager.getConnection(url, user, password); //quem eu quero acessar
	return con;

	} catch (Exception e) {
	// TODO: handle exception
	System.out.println(e);
	return null;
	}
	}
	
	public void inserirClientes(JavaBeans clientes) {
		String SQLinsert = 
		"insert into clientes(nome,fone,email,endereco,bairro,obs) values(?,?,?,?,?,?);";
		
		try {
			Connection con = conectar();
			// Prepara a query
			PreparedStatement pst= con.prepareStatement(SQLinsert);
			//substituir as ??
			pst.setString(1,clientes.getNome());
			pst.setString(2,clientes.getFone());
			pst.setString(3,clientes.getEmail());
			pst.setString(4,clientes.getEndereco());
			pst.setString(5,clientes.getBairro());
			pst.setString(6,clientes.getObs());
			
			System.out.println(pst);
			
			//Executar SQL
			
			pst.executeUpdate();
			
			//Encerrar a conexão
			con.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	//CRUD READ
		public ArrayList<JavaBeans> listaClientes(){
			ArrayList<JavaBeans> clientes = new ArrayList<JavaBeans>();
			String sqlRead = "select * from clientes order by nome";
			try {
				Connection con = conectar();
				//preparar a query
				PreparedStatement pst = con.prepareStatement(sqlRead);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					String idcon = rs.getString(1);
					String nome = rs.getString(2);
					String fone = rs.getString(3);
					String email = rs.getString(4);
					
					
					
					clientes.add(new JavaBeans(idcon,nome,fone,email));
					
				}
				con.close();
				return clientes;
			}catch (Exception e) {
				System.out.println(e);
				return null;
			}
		}
	
		// CRUD SELECIONAR//
		public void selecionarClientes(JavaBeans clientes){
			String sqlSelect = "select * from clientes where idcon=?";
			try {
				Connection con = conectar();
				//preparar a query
				PreparedStatement pst = con.prepareStatement(sqlSelect);
				
				pst.setString(1, clientes.getIdcon());
				
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					clientes.setIdcon(rs.getString(1));
					clientes.setNome(rs.getString(2));
					clientes.setFone(rs.getString(3));
					clientes.setEmail(rs.getString(4));
					
				}
				con.close();
			}catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
		public void alterarClientes(JavaBeans clientes) {
			String sqlUpdate = "update clientes set nome=?, fone=?, email=? where idcon=?";
			try{
				Connection con = conectar();
				//Prepara a query
				PreparedStatement pst = con.prepareStatement(sqlUpdate);
				//Substituir os paramentros
				pst.setString(1, clientes.getNome());
				pst.setString(2, clientes.getFone());
				pst.setString(3, clientes.getEmail());
				pst.setString(4, clientes.getIdcon());
				
				pst.executeUpdate();
				con.close();
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		
		public void removerClientes(JavaBeans clientes) {
			String sqlUpdate = "delete from clientes where idcon = ?";
			
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(sqlUpdate);
				pst.setString(1, clientes.getIdcon());
				pst.executeUpdate();
				
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
}
