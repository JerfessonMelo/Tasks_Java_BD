import java.time.LocalDateTime;

public class HistoricoEntregas extends Base{

    private LocalDateTime dataHora;
    private StatusEntrega statusEntrega;
    private Pedido pedido;



    public HistoricoEntregas(int id, int status, LocalDateTime dataCreat2, LocalDateTime dataHora, 
        StatusEntrega statusEntrega, Pedido pedido) {
        super(id, status, dataCreat2);
        this.dataHora = dataHora;
        this.statusEntrega = statusEntrega;
        this.pedido = pedido;
    }

    public HistoricoEntregas(int id, int status, LocalDateTime dataCreat2, LocalDateTime dataHora) {
        super(id, status, dataCreat2);
        this.dataHora = dataHora;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusEntrega getStatusEntrega() {
        return statusEntrega;
    }

    public void setStatusEntrega(StatusEntrega statusEntrega) {
        this.statusEntrega = statusEntrega;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    
}
