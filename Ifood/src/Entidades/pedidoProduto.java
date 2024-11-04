import java.time.LocalDateTime;

public class PedidoProduto extends Base {

    private int quantidade;
    private Pedido pedido;
    private Produtos produtos;

    public PedidoProduto(int id, int status, LocalDateTime dataCreat, int quantidade) {
        super(id, status, dataCreat);
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produtos getProdutos() {
        return produtos;
    }

    public void setProdutos(Produtos produtos) {
        this.produtos = produtos;
    }

}
