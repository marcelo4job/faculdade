/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Renan
 */
public class CadastroEndereco {

    private int id;
    private int Endereco_idEndereco;
    private String Logradouro;
    private String Numero;
    private String Complemento;
    private String Bairro;
    private String UF;
    private String Pais;
    private String Cep;
    private String Localidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEndereco_idEndereco() {
        return Endereco_idEndereco;
    }

    public void setEndereco_idEndereco(int Endereco_idEndereco) {
        this.Endereco_idEndereco = Endereco_idEndereco;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String Logradouro) {
        this.Logradouro = Logradouro;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String Complemento) {
        this.Complemento = Complemento;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String Bairro) {
        this.Bairro = Bairro;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String Cep) {
        this.Cep = Cep;
    }

    /**
     * @return the Localidade
     */
    public String getLocalidade() {
        return Localidade;
    }

    /**
     * @param Localidade the Localidade to set
     */
    public void setLocalidade(String Localidade) {
        this.Localidade = Localidade;
    }

}
