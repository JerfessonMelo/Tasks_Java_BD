import java.time.LocalDate;

public class historicoPagamento extends Base{

    private float valor;
    private LocalDate dataPagamento;
    
    public historicoPagamento(int id, String status, LocalDate dataCreat, float valor, LocalDate dataPagamento) {
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
    public LocalDate getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

}
