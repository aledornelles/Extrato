package DAO.Postgres;

import DAO.ContaDAO;
import Models.Conta;
import Models.ContaCorrente;
import Models.ContaPoupanca;
import Models.ContaSalario;
import Models.Pessoa;
import Models.PessoaFisica;
import Models.PessoaJuridica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ContaDAOPostegres implements ContaDAO {

    private final Connection conexao;

    public ContaDAOPostegres(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public void insereConta(Conta conta) {
        String sql = "INSERT INTO conta (id, numero, saldo, tipo, pessoa_id, senha) VALUES (?, ?, ?, CAST (? as tipo_conta), ?, ?)";
        String tipoConta = (conta instanceof ContaCorrente) ? "corrente" : (conta instanceof ContaSalario) ? "salario" : "poupanca";

        try {
            PreparedStatement stm = this.conexao.prepareStatement(sql);
            stm.setObject(1, conta.getId());
            stm.setInt(2, conta.getNumero());
            stm.setDouble(3, conta.getSaldo());
            stm.setString(4, tipoConta);
            stm.setObject(5, conta.getTitular().getId());
            stm.setString(6, conta.getSenha());

            stm.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }

    @Override
    public Conta buscarContaPorDocumentoTitular(String documento) {
        String sql = "SELECT c.id, c.numero, c.saldo, c.tipo, c.pessoa_id, c.senha, p.nome, p.tipo AS pessoa_tipo FROM conta AS c"
                + " JOIN pessoa AS p ON c.pessoa_id = p.id WHERE p.documento = ?";

        try {
            PreparedStatement stm = this.conexao.prepareStatement(sql);
            stm.setString(1, documento);

            ResultSet resultado = stm.executeQuery();
            if (resultado.next()) {
                UUID id = resultado.getObject("pessoa_id", UUID.class);
                String nome = resultado.getString("nome");
                String tipo = resultado.getString("pessoa_tipo");
                Pessoa pessoa = (tipo.equals("PF")) ? new PessoaFisica(id, nome, documento) : new PessoaJuridica(id, nome, documento);

                UUID idConta = resultado.getObject("id", UUID.class);
                int numero = resultado.getInt("numero");
                double saldo = resultado.getDouble("saldo");
                String tipoC = resultado.getString("tipo");
                String senha = resultado.getString("senha");

                switch (tipoC) {
                    case "corrente":
                        return new ContaCorrente(idConta, numero, saldo, pessoa, senha);
                    case "poupanca":
                        return new ContaPoupanca(idConta, numero, saldo, pessoa, senha);
                    case "salario":
                        return new ContaSalario(idConta, numero, saldo, pessoa, senha);
                    default:
                        return null;
                }
            }
        } catch (SQLException error) {
            System.out.println(error);
        }
        return null;
    }

    @Override
    public void atualizaSaldo(Conta conta) {
        String sql = "UPDATE conta SET saldo = ? WHERE id = ?";

        try {
            PreparedStatement stm = this.conexao.prepareStatement(sql);
            stm.setDouble(1, conta.getSaldo());
            stm.setObject(2, conta.getId());
            stm.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error);
        }
    }
}
