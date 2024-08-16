package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DbException;

public class Programa {

	public static void main(String[] args) {
		
	//CLASSE STATEMENT - SERVE PARA MONTAR UM COMANDO SQL PARA SER EXECUTADO
	//CLASE RESULTSET - REPRESENTA UM OBJETO CONTENDO UM RESULTADO DA NOSSA	CONSULTA EM FORMA DE TABELA
		
		Connection conn = null;//variável de conexão conn
		Statement st = null;// variável do tipo statement
		ResultSet rs = null;//variável do tipo resulset
		
		try {
			//variável conn recebe a classe DB.getconnection para conectar com o banco de dados
			conn = DB.getConnection();
			
			//instância do objeto do tipo statement
			st = conn.createStatement();
			
			//este metodo espera a uma string, que é um comando sql
			//e o resultado dessa consulta vou atribuir na variável rs
			rs = st.executeQuery("select * from department");
			
			//enquanto existir o próximo "next()"
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
