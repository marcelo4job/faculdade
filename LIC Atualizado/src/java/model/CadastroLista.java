/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author anobr
 */
public class CadastroLista {

    private int idLista;
    private String nome;
    private String idSupermercado;
    private String idUsuarioCadastro;
    private String idTipoUsuario;
    private String precoTotalLista;

    /**
     * @return the idLista
     */
    public int getIdLista() {
        return idLista;
    }

    /**
     * @param idLista the idLista to set
     */
    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the idSupermercado
     */
    public String getIdSupermercado() {
        return idSupermercado;
    }

    /**
     * @param idSupermercado the idSupermercado to set
     */
    public void setIdSupermercado(String idSupermercado) {
        this.idSupermercado = idSupermercado;
    }

    /**
     * @return the idUsuarioCadastro
     */
    public String getIdUsuarioCadastro() {
        return idUsuarioCadastro;
    }

    /**
     * @param idUsuarioCadastro the idUsuarioCadastro to set
     */
    public void setIdUsuarioCadastro(String idUsuarioCadastro) {
        this.idUsuarioCadastro = idUsuarioCadastro;
    }

    /**
     * @return the idTipoUsuario
     */
    public String getIdTipoUsuario() {
        return idTipoUsuario;
    }

    /**
     * @param idTipoUsuario the idTipoUsuario to set
     */
    public void setIdTipoUsuario(String idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

    /**
     * @return the precoTotalLista
     */
    public String getPrecoTotalLista() {
        return precoTotalLista;
    }

    /**
     * @param precoTotalLista the precoTotalLista to set
     */
    public void setPrecoTotalLista(String precoTotalLista) {
        this.precoTotalLista = precoTotalLista;
    }

}
