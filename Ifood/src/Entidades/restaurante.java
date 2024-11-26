import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Timer;

public class Restaurante extends Base{

    private String nome;
    private String telefone;
    private float valorMinimoEntrega;
    private Boolean isRetirada;
    private Timer HoraFuncionamento;
    private Endereco endereco;
    private Categoria categoria;
    private ArrayList<Produto> produto = new ArrayList<>();

    public Restaurante(int id, int status, LocalDateTime dataCreat2, String nome, String telefone, float valorMinimoEntrega,
            Boolean isRetirada, Timer horaFuncionamento, Endereco endereco, Categoria categoria) {
        super(id, status, dataCreat2);
        this.nome = nome;
        this.telefone = telefone;
        this.valorMinimoEntrega = valorMinimoEntrega;
        this.isRetirada = isRetirada;
        HoraFuncionamento = horaFuncionamento;
        this.endereco = endereco;
        this.categoria = categoria;
    }
  
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public float getValorMinimoEntrega() {
        return valorMinimoEntrega;
    }
    public void setValorMinimoEntrega(float valorMinimoEntrega) {
        this.valorMinimoEntrega = valorMinimoEntrega;
    }
    public Boolean getIsRetirada() {
        return isRetirada;
    }
    public void setIsRetirada(Boolean isRetirada) {
        this.isRetirada = isRetirada;
    }
    public Timer getHoraFuncionamento() {
        return HoraFuncionamento;
    }
    public void setHoraFuncionamento(Timer horaFuncionamento) {
        HoraFuncionamento = horaFuncionamento;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public ArrayList<Produto> getProdutos() {
        return produto;
    }
    public void setProdutos(ArrayList<Produto> produto) {
        this.produto = produto;
    }
    
}
