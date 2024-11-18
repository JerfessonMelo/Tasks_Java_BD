import java.time.LocalDateTime;
import java.util.ArrayList;

public class Avaliacao extends Base {

    private int nota;
    private ArrayList<Pedido> pedido = new ArrayList<>();

    public Avaliacao(int id, int status, LocalDateTime dataCreat, int nota) {
        super(id, status, dataCreat);
        this.nota = nota;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public ArrayList<Pedido> getPedido() {
        return pedido;
    }

    public void setPedido(ArrayList<Pedido> pedido) {
        this.pedido = pedido;
    }

}
