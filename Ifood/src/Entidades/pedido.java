import java.time.LocalDate;

public class pedido extends Base {

    private LocalDate dataPedido;
    private float valor;
    private float taxaEntrega;
    private String observacoes;
    private Float troco;
    
    public pedido(int id, String status, LocalDate dataCreat, LocalDate dataPedido, float valor, float taxaEntrega,
            String observacoes, Float troco) {
        super(id, status, dataCreat);
        this.dataPedido = dataPedido;
        this.valor = valor;
        this.taxaEntrega = taxaEntrega;
        this.observacoes = observacoes;
        this.troco = troco;
    }
    public LocalDate getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
    public float getTaxaEntrega() {
        return taxaEntrega;
    }
    public void setTaxaEntrega(float taxaEntrega) {
        this.taxaEntrega = taxaEntrega;
    }
    public String getObservacoes() {
        return observacoes;
    }
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
    public Float getTroco() {
        return troco;
    }
    public void setTroco(Float troco) {
        this.troco = troco;
    }
}
