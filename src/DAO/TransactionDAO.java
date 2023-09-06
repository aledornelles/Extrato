package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Models.Transaction;

public class TransactionDAO {
    private Connection connection;

    public TransactionDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions (data, tipoTransacao, valorTransacao, saldoResultante) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTimestamp(1, new java.sql.Timestamp(transaction.getData().getTime()));
            statement.setString(2, transaction.getTipoTransacao());
            statement.setDouble(3, transaction.getValorTransacao());
            statement.setDouble(4, transaction.getSaldoResultante());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date data = resultSet.getTimestamp("data");
                String tipoTransacao = resultSet.getString("tipoTransacao");
                double valorTransacao = resultSet.getDouble("valorTransacao");
                double saldoResultante = resultSet.getDouble("saldoResultante");

                Transaction transaction = new Transaction(id, data, tipoTransacao, valorTransacao, saldoResultante);
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}
