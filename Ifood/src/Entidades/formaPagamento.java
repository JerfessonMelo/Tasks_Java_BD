import java.time.LocalDate;

public class formaPagamento extends Base{

    private String forma;

    public formaPagamento(int id, String status, LocalDate dataCreat, String forma) {
        super(id, status, dataCreat);
        this.forma = forma;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }
}
