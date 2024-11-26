import java.time.LocalDateTime;
import java.util.ArrayList;

public class HistoricoPagamento extends Base{

    private float valor;
    private LocalDateTime dataPagamento;
    private Pedido pedido;
    private ArrayList<FormaPagamento> formaPagamentos = new ArrayList<>();
  
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
    public ArrayList<FormaPagamento> getFormaPagamentos() {
        return formaPagamentos;
    }
    public void setFormaPagamentos(ArrayList<FormaPagamento> formaPagamentos) {
        this.formaPagamentos = formaPagamentos;
    }
    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
   
}
