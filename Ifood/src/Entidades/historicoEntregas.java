import java.time.LocalDate;

public class historicoEntregas extends Base{

    private LocalDate dataHora;

    public historicoEntregas(int id, String status, LocalDate dataCreat, LocalDate dataHora) {
        super(id, status, dataCreat);
        this.dataHora = dataHora;
    }

    public LocalDate getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDate dataHora) {
        this.dataHora = dataHora;
    }

}
