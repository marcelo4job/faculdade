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
public class CadastroListaItens {

    private int idLista;
    private Double preco;
    private String nome;
    private int quantidade;
    private Double precoTotalLista;
    private int idItem;

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
     * @return the preco
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(Double preco) {
        this.preco = preco;
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
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the precoTotalLista
     */
    public Double getPrecoTotalLista() {
        return precoTotalLista;
    }

    /**
     * @param precoTotalLista the precoTotalLista to set
     */
    public void setPrecoTotalLista(Double precoTotalLista) {
        this.precoTotalLista = precoTotalLista;
    }

    /**
     * @return the idItem
     */
    public int getIdItem() {
        return idItem;
    }

    /**
     * @param idItem the idItem to set
     */
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }



}
