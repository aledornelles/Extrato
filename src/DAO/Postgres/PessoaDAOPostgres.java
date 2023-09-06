package DAO.Postgres;

import DAO.PessoaDAO;
import Models.Pessoa;
import Models.PessoaFisica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class PessoaDAOPostgres implements PessoaDAO {

    private final Connection conexao;

    public PessoaDAOPostgres(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void inserirPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa (id, nome, documento, tipo) VALUES (?, ?, ?, CAST(? as tipo_pessoa))";
        String tipoPessoa = (pessoa instanceof PessoaFisica) ? "PF" : "PJ";
        
        try {
            PreparedStatement stm = this.conexao.prepareStatement(sql);
            stm.setObject(1, pessoa.getId());
            stm.setString(2, pessoa.getNome());
            stm.setString(3, pessoa.getDocumento());
            stm.setString(4, tipoPessoa);

            stm.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    @Override
    public Pessoa obterPessoaPeloId(UUID Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
