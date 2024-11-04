import java.time.LocalDateTime;

public class FormaPagamento extends Base{

    private String formaPag;
    private HistoricoPagamento historicoPagamento;

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

    public HistoricoPagamento getHistoricoPagamento() {
        return historicoPagamento;
    }

    public void setHistoricoPagamento(HistoricoPagamento historicoPagamento) {
        this.historicoPagamento = historicoPagamento;
    }

    
}
