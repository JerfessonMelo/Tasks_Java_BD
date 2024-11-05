import java.time.LocalDateTime;

public class FormaPagamento extends Base{

    private String formaPag;
    private HistoricoPagamento historicoPagamento;
    private Pedido pedido;

    public FormaPagamento(int id, int status, LocalDateTime dataCreat, String formaPag) {
        super(id, status, dataCreat);
        this.formaPag = formaPag;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

    public HistoricoPagamento getHistoricoPagamento() {
        return historicoPagamento;
    }

    public void setHistoricoPagamento(HistoricoPagamento historicoPagamento) {
        this.historicoPagamento = historicoPagamento;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
      
}
