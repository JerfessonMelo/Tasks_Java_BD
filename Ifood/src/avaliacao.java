import java.time.LocalDate;

public class avaliacao extends Base {

    private int nota;

    public avaliacao(int id, String status, LocalDate dataCreat, int nota) {
        super(id, status, dataCreat);
        this.nota = nota;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

}
