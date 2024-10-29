import java.time.LocalDate;

public class produtos extends Base {

    private String nome;
    private float preco;
    
    public produtos(int id, String status, LocalDate dataCreat, String nome, float preco) {
        super(id, status, dataCreat);
        this.nome = nome;
        this.preco = preco;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getPreco() {
        return preco;
    }
    public void setPreco(float preco) {
        this.preco = preco;
    }

}
