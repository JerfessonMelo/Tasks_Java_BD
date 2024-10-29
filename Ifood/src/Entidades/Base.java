import java.time.LocalDate;
public abstract class Base {


    private int id;
    private String status;
    private LocalDate dataCreat;

    public Base(int id, String status, LocalDate dataCreat) {
        this.id = id;
        this.status = status;
        this.dataCreat = dataCreat;
    }
    public LocalDate getDataCreat() {
        return dataCreat;
    }
    public void setDataCreat(LocalDate dataCreat) {
        this.dataCreat = dataCreat;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    

}
