import java.time.LocalDateTime;

public class FormaPagamento extends Base{

    private String formaPagamento;
    private HistoricoPagamento historicoPagamento;
    private Pedido pedido;

    public FormaPagamento(int id, int status, LocalDateTime dataCreat2, String formaPagamento) {
        super(id, status, dataCreat2);
        this.formaPagamento = formaPagamento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagagamento(String formaPag) {
        this.formaPagamento = formaPag;
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

    @Override
    public String toString() {
        return this.formaPagamento;
    }  
}
