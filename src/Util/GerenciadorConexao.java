package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static semana05.Semana05.dotenv;

public class GerenciadorConexao {
    private static Connection conexao;
    
    public static Connection getConexao() {
        if (conexao == null) {
            try {
                String URL = dotenv.get("DB_URL");
                String user = dotenv.get("DB_USER");
                String password = dotenv.get("DB_PASSWORD");
                
                conexao = DriverManager.getConnection(URL, user, password);
                System.out.println("Conexão com o banco de dados estabelecida!");
            } catch (SQLException e) {
                System.out.println("Falha ao conectar ao banco de dados");
                e.printStackTrace();
                // Aqui, você pode lançar uma exceção personalizada ou realizar outras ações de tratamento de erro.
            }
        }
        return conexao;
    }
    
    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão com o banco de dados fechada!");
            } catch (SQLException e) {
                System.out.println("Falha ao fechar a conexão com o banco de dados");
                e.printStackTrace();
            }
        }
    }
}
