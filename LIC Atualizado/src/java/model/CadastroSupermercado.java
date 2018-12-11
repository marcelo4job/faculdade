package model;

/**
 *
 * @author Renan
 */
public class CadastroSupermercado {

    private int id;
    private int Endereco_idEndereco;
    private String RazaoSocialSupermercado;
    private String NomeFantasiaSupermercado;
    private String cnpjSupermercado;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public int getEndereco_idEndereco() {
        return Endereco_idEndereco;
    }

    public void setEndereco_idEndereco(int Endereco_idEndereco) {
        this.Endereco_idEndereco = Endereco_idEndereco;
    }

    /**
     * @return the RazaoSocialSupermercado
     */
    public String getRazaoSocialSupermercado() {
        return RazaoSocialSupermercado;
    }

    /**
     * @param RazaoSocialSupermercado the RazaoSocialSupermercado to set
     */
    public void setRazaoSocialSupermercado(String RazaoSocialSupermercado) {
        this.RazaoSocialSupermercado = RazaoSocialSupermercado;
    }

    /**
     * @return the NomeFantasiaSupermercado
     */
    public String getNomeFantasiaSupermercado() {
        return NomeFantasiaSupermercado;
    }

    /**
     * @param NomeFantasiaSupermercado the NomeFantasiaSupermercado to set
     */
    public void setNomeFantasiaSupermercado(String NomeFantasiaSupermercado) {
        this.NomeFantasiaSupermercado = NomeFantasiaSupermercado;
    }

    public String getCnpjSupermercado() {
        return cnpjSupermercado;
    }

    /**
     * @param cnpjSupermercado the cnpjSupermercadoto set
     */
    public void setCnpjSupermercado(String cnpjSupermercado) {
        this.cnpjSupermercado = cnpjSupermercado;
    }

}
