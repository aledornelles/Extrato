package Models;

import java.util.UUID;

public class ContaSalario extends Conta {

    public ContaSalario(Pessoa titular, String senha) {
        super(titular, senha);
    }

    public ContaSalario(UUID id, int numero, double saldo, Pessoa titular, String senha) {
        super(id, numero, saldo, titular, senha);
    }

}
