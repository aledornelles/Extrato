package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import Util.GerenciadorConexao;

public class ExtratoController {
    private Connection conexao;

    public ExtratoController() {
        conexao = GerenciadorConexao.getConexao();
    }

    public void realizarTransacao(String tipoTransacao, double valorTransacao, double saldoAnterior) {
        try {
            Date data = new Date();
            double saldoResultante = (tipoTransacao.equals("entrada")) ? saldoAnterior + valorTransacao : saldoAnterior - valorTransacao;

            String sql = "INSERT INTO transacoes (data, tipo_transacao, valor_transacao, saldo_resultante) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setTimestamp(1, new Timestamp(data.getTime()));
            statement.setString(2, tipoTransacao);
            statement.setDouble(3, valorTransacao);
            statement.setDouble(4, saldoResultante);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao realizar a transação no banco de dados");
            e.printStackTrace();
        }
    }

    public ResultSet consultarExtrato() {
        try {
            String sql = "SELECT * FROM transacoes";
            PreparedStatement statement = conexao.prepareStatement(sql);
            return statement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Erro ao consultar o extrato no banco de dados");
            e.printStackTrace();
            return null;
        }
    }

    public void fecharConexao() {
        GerenciadorConexao.fecharConexao();
    }
}
