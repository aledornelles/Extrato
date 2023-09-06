package DAO.Mysql;

import DAO.PessoaDAO;
import Models.Pessoa;
import java.sql.Connection;
import java.util.UUID;

public class PessoaDAOMysql implements PessoaDAO{

    public PessoaDAOMysql(Connection conexao) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void inserirPessoa(Pessoa pessoa) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pessoa obterPessoaPeloId(UUID Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
