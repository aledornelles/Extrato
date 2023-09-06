package Models;

import java.util.Date;

public class Transaction {
    private int id;
    private Date data;
    private String tipoTransacao;
    private double valorTransacao;
    private double saldoResultante;

    public Transaction(int id, Date data, String tipoTransacao, double valorTransacao, double saldoResultante) {
        if (id < 0) {
            this.id = 0; 
        } else {
            this.id = id;
        }
        this.data = data;
        this.tipoTransacao = tipoTransacao;
        this.valorTransacao = valorTransacao;
        this.saldoResultante = saldoResultante;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            this.id = 0; 
        } else {
            this.id = id;
        }
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public double getSaldoResultante() {
        return saldoResultante;
    }

    public void setSaldoResultante(double saldoResultante) {
        this.saldoResultante = saldoResultante;
    }
}
