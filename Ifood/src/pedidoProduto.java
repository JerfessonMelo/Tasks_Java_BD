import java.time.LocalDate;

public class pedidoProduto extends Base {

    private int quantidade;

    public pedidoProduto(int id, String status, LocalDate dataCreat, int quantidade) {
        super(id, status, dataCreat);
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
