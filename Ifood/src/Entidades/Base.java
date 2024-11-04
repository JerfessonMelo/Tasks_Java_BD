import java.time.LocalDateTime;
public abstract class Base {

    public static final int DISABLED = 0;
    public static final int ENABLED = 1;
    public static final int REMOVED = 2;

    protected int id;
    protected int status = ENABLED;
    protected LocalDateTime dataCreat;
    
    public Base(int id, int status, LocalDateTime dataCreat2) {
        this.id = id;
        this.status = status;
        this.dataCreat = LocalDateTime.now();    
    }

    public Boolean isActive() {
        return this.status == ENABLED;
    }

    public static int getDisabled() {
        return DISABLED;
    }

    public static int getEnabled() {
        return ENABLED;
    }

    public static int getRemoved() {
        return REMOVED;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDateTime getDataCreat() {
        return dataCreat;
    }

    public void setDataCreat(LocalDateTime dataCreat) {
        this.dataCreat = dataCreat;
    }

}
