import java.time.LocalDate;

public class promocao extends Base {

    private String nome;
    private String codigo;
    private float valor;
    private LocalDate validade;
    private String tipo;
    
    public promocao(int id, String status, LocalDate dataCreat, String nome, String codigo, float valor,
            LocalDate validade, String tipo) {
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
    public LocalDate getValidade() {
        return validade;
    }
    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
