package Models;

import java.util.UUID;

public class PessoaJuridica extends Pessoa {

    private String cnpj;

    public PessoaJuridica(String nome, String cnpj) {
        super(nome, cnpj);
        this.cnpj = cnpj;
    }

    public PessoaJuridica(UUID id, String nome, String cnpj) {
        super(id, nome, cnpj);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    @Override
    public boolean validaDocumento(String documento) {
        // VALIDA O CNPJ;
        return true;
    }
}
