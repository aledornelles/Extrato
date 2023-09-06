package Models;

import java.util.UUID;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Pessoa titular, String senha) {
        super(titular, senha);
    }

    public ContaPoupanca(UUID id, int numero, double saldo, Pessoa titular, String senha) {
        super(id, numero, saldo, titular, senha);
    }

}
