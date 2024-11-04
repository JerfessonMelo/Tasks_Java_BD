import java.time.LocalDateTime;

public class HistoricoPagamento extends Base{

    private float valor;
    private LocalDateTime dataPagamento;
    private FormaPagamento formaPagamento;

    public HistoricoPagamento(int id, int status, LocalDateTime dataCreat, float valor, LocalDateTime dataPagamento) {
        super(id, status, dataCreat);
        this.valor = valor;
        this.dataPagamento = dataPagamento;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
   
}
