import java.time.LocalDateTime;

public class Acompanhamento extends Base {

    private String nome;
    private String descricao;
    private float valor;
    
    public Acompanhamento(int id, int status, LocalDateTime dataCreat, String nome, String descricao, float valor) {
        super(id, status, dataCreat);
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
}
