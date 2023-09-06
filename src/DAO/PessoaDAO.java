 package DAO;

import Models.Pessoa;
import java.util.UUID;

public interface PessoaDAO {
    public void inserirPessoa(Pessoa pessoa);
    public Pessoa obterPessoaPeloId(UUID Id);
}
