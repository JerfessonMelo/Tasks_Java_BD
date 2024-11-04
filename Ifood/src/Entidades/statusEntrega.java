import java.time.LocalDateTime;

public class StatusEntrega extends Base{

    private String nome;

    public StatusEntrega(int id, int status, LocalDateTime dataCreat, String nome) {
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
