import java.time.LocalDateTime;
import java.util.ArrayList;

public class Categoria extends Base {

    private String nome;
    private String descricao;
    private String tipoCategoria;
    private ArrayList<Produto> produto;
    private ArrayList<Restaurante> restaurante = new ArrayList<>();

    
    public Categoria(int id, int status, LocalDateTime dataCreat, String nome, String descricao, String tipoCategoria) {
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

    public ArrayList<Produto> getProduto() {
        return produto;
    }

    public void setProduto(ArrayList<Produto> produto) {
        this.produto = produto;
    }

    public ArrayList<Restaurante> getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(ArrayList<Restaurante> restaurante) {
        this.restaurante = restaurante;
    }
        
}
