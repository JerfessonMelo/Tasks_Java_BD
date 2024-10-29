import java.time.LocalDate;

public class restaurante extends Base{

    private String nome;
    private String telefone;
    private float valorMinimoEntrega;
    
    public restaurante(int id, String status, LocalDate dataCreat, String nome, String telefone,
            float valorMinimoEntrega) {
        super(id, status, dataCreat);
        this.nome = nome;
        this.telefone = telefone;
        this.valorMinimoEntrega = valorMinimoEntrega;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public float getValorMinimoEntrega() {
        return valorMinimoEntrega;
    }
    public void setValorMinimoEntrega(float valorMinimoEntrega) {
        this.valorMinimoEntrega = valorMinimoEntrega;
    }

}
