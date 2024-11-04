import java.time.LocalDateTime;

public class Pedido extends Base {

    private LocalDateTime dataPedido;
    private float valor;
    private float taxaEntrega;
    private String observacoes;
    private Float troco;
    private Restaurante restaurante;
    private Promocao promocao;
    private FormaPagamento formaPagamento;
    private StatusEntrega statusEntrega;
    private Endereco endereco;
    
    public Pedido(int id, int status, LocalDateTime dataCreat, LocalDateTime dataPedido, float valor, float taxaEntrega,
            String observacoes, Float troco) {
        super(id, status, dataCreat);
        this.dataPedido = dataPedido;
        this.valor = valor;
        this.taxaEntrega = taxaEntrega;
        this.observacoes = observacoes;
        this.troco = troco;
    }
    public LocalDateTime getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(LocalDateTime dataPedido) {
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
    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
    public Promocao getPromocao() {
        return promocao;
    }
    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    public StatusEntrega getStatusEntrega() {
        return statusEntrega;
    }
    public void setStatusEntrega(StatusEntrega statusEntrega) {
        this.statusEntrega = statusEntrega;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
}
