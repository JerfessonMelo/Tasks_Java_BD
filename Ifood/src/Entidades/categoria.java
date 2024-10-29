import java.time.LocalDate;

public class categoria extends Base {

    private String nome;
    private String descricao;
    private String tipoCategoria;
    
    public categoria(int id, String status, LocalDate dataCreat, String nome, String descricao, String tipoCategoria) {
        super(id, status, dataCreat);
        this.nome = nome;
        this.descricao = descricao;
        this.tipoCategoria = tipoCategoria;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getTipoCategoria() {
        return tipoCategoria;
    }
    public void setTipoCategoria(String tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }

}
