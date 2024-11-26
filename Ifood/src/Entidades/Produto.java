import java.time.LocalDateTime;

public class Produto extends Base {

    private String nome;
    private float preco;
    private Categoria categoria;
    private Restaurante restaurante;
    private PedidoProduto pedidoProduto;
    
    public Produto(int id, int status, LocalDateTime dataCreat2, String nome, float preco) {
        super(id, status, dataCreat2);
        this.nome = nome;
        this.preco = preco;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
    public PedidoProduto getPedidoProduto() {
        return pedidoProduto;
    }
    public void setPedidoProduto(PedidoProduto pedidoProduto) {
        this.pedidoProduto = pedidoProduto;
    }  
}
