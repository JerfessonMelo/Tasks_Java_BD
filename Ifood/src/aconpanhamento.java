import java.time.LocalDate;

public class aconpanhamento extends Base {

    private String nome;
    private String descricao;
    private float valor;

    public aconpanhamento(int id, String status, LocalDate dataCreat, String nome, String descricao, float valor) {
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
