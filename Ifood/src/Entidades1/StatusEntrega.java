package Entidades1;
import java.time.LocalDateTime;

public class StatusEntrega extends Base{

    private StatusEntregaEnum statusEntrega;
    private Pedido pedido;

    public StatusEntrega(int id, int status, LocalDateTime dataCriacao, StatusEntregaEnum statusEntrega, Pedido pedido) {
        super(id, status, dataCriacao);
        this.statusEntrega = statusEntrega;
        this.pedido = pedido;
    }

    public void definirStatusEntrega(StatusEntregaEnum status) {
        this.statusEntrega = status;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public StatusEntregaEnum getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(StatusEntregaEnum statusEntrega) {
        this.statusEntrega = statusEntrega;
    }
}
