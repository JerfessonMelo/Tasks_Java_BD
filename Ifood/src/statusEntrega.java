import java.time.LocalDate;

public class statusEntrega extends Base{

    private String nome;

    public statusEntrega(int id, String status, LocalDate dataCreat, String nome) {
        super(id, status, dataCreat);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
