import java.time.LocalDateTime;

public class Promocao extends Base {

    private String nome;
    private String codigo;
    private float valor;
    private LocalDateTime validade;
    private String tipo;
    private Pedido pedido;
    
    public Promocao(int id, int status, LocalDateTime dataCreat, String nome, String codigo, float valor,
            LocalDateTime validade, String tipo) {
        super(id, status, dataCreat);
        this.nome = nome;
        this.codigo = codigo;
        this.valor = valor;
        this.validade = validade;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public LocalDateTime getValidade() {
        return validade;
    }

    public void setValidade(LocalDateTime validade) {
        this.validade = validade;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    
}
