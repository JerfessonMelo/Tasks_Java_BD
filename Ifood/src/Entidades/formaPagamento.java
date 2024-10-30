import java.time.LocalDate;

public class formaPagamento extends Base{

    private FormaPag formaPag;

    private enum FormaPag{
        Pix, Cartao, Dinheiro
    }

    public formaPagamento(int id, String status, LocalDate dataCreat, formaPagamento.FormaPag formaPag) {
        super(id, status, dataCreat);
        this.formaPag = formaPag;
    }

    public FormaPag getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(FormaPag formaPag) {
        this.formaPag = formaPag;
    }

}
