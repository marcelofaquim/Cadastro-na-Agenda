package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Result;

public class DAO {
	//parametro de conexão
	private String driver="com.mysql.cj.jdbc.Driver";
	private String url="jdbc:mysql://127.0.0.1:3306/dbagenda?useTimezone=true&serverTimezone=UTC";
	private String user="root";
	private String password="";
	
	//conexão
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con =DriverManager.getConnection(url, user, password);
			return con;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		
	}
	
	public void inserirContato(JavaBeans contato) {
		String SQLinsert = "insert into contatos(nome,fone,email,obs) values (?,?,?,?);";
		
		try {
			Connection con = conectar();
			//preparar a query
			PreparedStatement pst = con.prepareStatement(SQLinsert);
			//substituir as ?
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getObs());
			
			pst.executeUpdate();

			//Executar SQL
			con.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
	//CRUD READ
	public ArrayList<JavaBeans> listaContato(){
		ArrayList<JavaBeans> contatos = new ArrayList<JavaBeans>();
		String sqlRead = "select * from contatos order by nome";
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
				
				contatos.add(new JavaBeans(idcon,nome,fone,email));
				
			}
			con.close();
			return contatos;
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	// CRUD SELECIONAR//
	public void selecionarContato(JavaBeans contato){
		String sqlRead = "select * from contatos where idcon=?";
		try {
			Connection con = conectar();
			//preparar a query
			PreparedStatement pst = con.prepareStatement(sqlRead);
			
			pst.setString(1, contato.getIdcon());
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contato.setIdcon(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setFone(rs.getString(3));
				contato.setEmail(rs.getString(4));
				
			}
			con.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	//CRUD UPdate//
	public void alterarContato(JavaBeans contato) {
		String sqlUpdate = "update contatos set nome=?, fone=?, email=? where idcon=?";
		try{
			Connection con = conectar();
			//Prepara a query
			PreparedStatement pst = con.prepareStatement(sqlUpdate);
			//Substituir os paramentros
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getFone());
			pst.setString(3, contato.getEmail());
			pst.setString(4, contato.getIdcon());
			
			pst.executeUpdate();
			con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void removerContato(JavaBeans contatos) {
		String sqlUpdate = "delete from contatos where idcon = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(sqlUpdate);
			pst.setString(1, contatos.getIdcon());
			pst.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
}


