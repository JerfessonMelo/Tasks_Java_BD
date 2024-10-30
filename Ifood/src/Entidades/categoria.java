import java.time.LocalDate;

public class categoria extends Base {

    private String nome;
    private String descricao;
    private TipoCategoria tipoCategoria;

    private enum TipoCategoria{
        restaurante, produto
    }

    public categoria(int id, String status, LocalDate dataCreat, String nome, String descricao,
            TipoCategoria tipoCategoria) {
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

    public TipoCategoria getTipoCategoria() {
        return tipoCategoria;
    }

    public void setTipoCategoria(TipoCategoria tipoCategoria) {
        this.tipoCategoria = tipoCategoria;
    }
      
}
