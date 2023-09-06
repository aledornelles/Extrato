package Factory;

import DAO.ContaDAO;
import DAO.Mysql.PessoaDAOMysql;
import DAO.PessoaDAO;
import DAO.Postgres.ContaDAOPostegres;
import DAO.Postgres.PessoaDAOPostgres;
import Util.GerenciadorConexao;
import java.sql.Connection;

public class FactoryDAO {

    public static ContaDAO makeContaDAO() {
        Connection conexao = GerenciadorConexao.getConexao();
        ContaDAOPostegres contaDao = new ContaDAOPostegres(conexao);
        return contaDao;
    }

    public static PessoaDAO makePessoaDAO() {
        Connection conexao = GerenciadorConexao.getConexao();
        PessoaDAO pessoaDao = new PessoaDAOPostgres(conexao);
        return pessoaDao;
    }

}
