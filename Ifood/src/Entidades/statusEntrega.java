import java.time.LocalDateTime;
import java.util.ArrayList;

public class StatusEntrega extends Base{

    private String nome;
    private ArrayList<Pedido> pedido;

    public StatusEntrega(int id, int status, LocalDateTime dataCreat, String nome) {
        super(id, status, dataCreat);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Pedido> pedido) {
        this.pedido = pedido;
    }
    

}
