import java.time.LocalDateTime;

public class FormaPagamento extends Base{

    private String formaPag;

    public FormaPagamento(int id, int status, LocalDateTime dataCreat, String formaPag) {
        super(id, status, dataCreat);
        this.formaPag = formaPag;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

}
