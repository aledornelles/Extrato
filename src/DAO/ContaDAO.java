package DAO;

import Models.Conta;

public interface ContaDAO {
    public void insereConta(Conta conta);
    public Conta buscarContaPorDocumentoTitular(String documento); 
    public void atualizaSaldo(Conta conta);
}
