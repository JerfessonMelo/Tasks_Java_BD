import java.time.LocalDateTime;
import java.util.ArrayList;

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
    private ArrayList<PedidoProduto> pedidoProduto = new ArrayList<>();
    private ArrayList<HistoricoEntregas> historicoEntregas;
    private ArrayList<HistoricoPagamento> historicoPagamentos;
    private ArrayList<Acompanhamento> acompanhamentos = new ArrayList<>();

    
    public Pedido(int id, int status, LocalDateTime dataCreat, LocalDateTime dataPedido, float valor, float taxaEntrega,
            String observacoes, Float troco, Restaurante restaurante, FormaPagamento formaPagamento, StatusEntrega statusEntrega, Endereco endereco) {
        super(id, status, dataCreat);
        this.dataPedido = dataPedido;
        this.valor = valor;
        this.taxaEntrega = taxaEntrega;
        this.observacoes = observacoes;
        this.troco = troco;
        this.restaurante = restaurante;
        this.formaPagamento = formaPagamento;
        this.statusEntrega = statusEntrega;
        this.endereco = endereco;
    }

    public void definirDataPedidoAutomaticamente() {
        this.dataPedido = LocalDateTime.now();
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
    public ArrayList<HistoricoEntregas> getHistoricoEntregas() {
        return historicoEntregas;
    }
    public void setHistoricoEntregas(ArrayList<HistoricoEntregas> historicoEntregas) {
        this.historicoEntregas = historicoEntregas;
    }
    public ArrayList<HistoricoPagamento> getHistoricoPagamentos() {
        return historicoPagamentos;
    }
    public void setHistoricoPagamentos(ArrayList<HistoricoPagamento> historicoPagamentos) {
        this.historicoPagamentos = historicoPagamentos;
    }
    public ArrayList<Acompanhamento> getAcompanhamentos() {
        return acompanhamentos;
    }
    public void setAcompanhamentos(ArrayList<Acompanhamento> acompanhamentos) {
        this.acompanhamentos = acompanhamentos;
    }
    public ArrayList<PedidoProduto> getPedidoProduto() {
        return pedidoProduto;
    }
    public void setPedidoProduto(ArrayList<PedidoProduto> pedidoProduto) {
        this.pedidoProduto = pedidoProduto;
    }
    
}
