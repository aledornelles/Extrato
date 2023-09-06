package Controllers;

import static Factory.FactoryDAO.makeContaDAO;
import Models.Conta;
import Models.ContaCorrente;
import Models.ContaPoupanca;
import Models.ContaSalario;
import Models.Pessoa;
import javax.swing.JOptionPane;
import semana05.Semana05;

public class ContaController {

    public int criarConta(Pessoa titular, boolean corrente, boolean poupanca, boolean salario, String senha) {
        Conta conta = corrente ? new ContaCorrente(titular, senha) : poupanca ? new ContaPoupanca(titular, senha) : new ContaSalario(titular, senha);

        makeContaDAO().insereConta(conta);
        return conta.getNumero();
    }

    public Conta buscarContaPorDocumentoTitular(String documento) {
        return makeContaDAO().buscarContaPorDocumentoTitular(documento);
    }

    public Conta buscarPorNumero(int numero) {
        for (Conta conta : Semana05.banco) {
            if (numero == conta.getNumero()) {
                return conta;
            }
        }
        return null;
    }

    public Conta depositar(Conta conta, double valor) {
        if (valor > 0) {
            conta.depositar(valor);
            makeContaDAO().atualizaSaldo(conta);
        } else {
            JOptionPane.showMessageDialog(null, "VALOR INVÁLIDO PARA DEPOSITO!");
        }
        return conta;
    }

    public Conta sacar(Conta conta, double valor) {
        if (valor <= conta.getSaldo() && valor > 0) {
            conta.sacar(valor);
            makeContaDAO().atualizaSaldo(conta);
        } else {
            JOptionPane.showMessageDialog(null, "VALOR INVÁLIDO OU INSUFICIENTE PARA SAQUE!");
        }
        return conta;
    }

    public Conta transferir(Conta conta, Conta destinatario, double valor) {

        if (conta.getSaldo() >= valor && valor > 0) {
            conta.transferir(destinatario, valor);
            makeContaDAO().atualizaSaldo(conta);
            makeContaDAO().atualizaSaldo(destinatario);
        } else {
            JOptionPane.showMessageDialog(null, "Valor inválido.");
        }

        return conta;

    }

}
