import java.time.LocalDateTime;
import java.util.ArrayList;

public class PedidoProduto extends Base {

    private int quantidade;
    private Pedido pedido;
    private double preco;
    private ArrayList<Produto> produto = new ArrayList<>();

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

    public ArrayList<Produto> getProduto() {
        return produto;
    }

    public void setProdutos(ArrayList<Produto> produto) {
        this.produto = new ArrayList<>(produto);
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

}
