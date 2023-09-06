package Models;

import java.util.UUID;

public class ContaCorrente extends Conta {

    public ContaCorrente(Pessoa titular, String senha) {
        super(titular, senha);
    }

    public ContaCorrente(UUID id, int numero, double saldo, Pessoa titular, String senha) {
        super(id, numero, saldo, titular, senha);
    }

    @Override
    public void sacar(double valor) {
        this.saldo = this.saldo - (valor + (this.saldo * 0.10));
    }

}
