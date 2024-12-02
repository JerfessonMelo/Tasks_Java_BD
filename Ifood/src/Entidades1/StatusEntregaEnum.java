package Entidades1;
public enum StatusEntregaEnum {
    EM_PRODUCAO(1, "Em Produção"),
    EM_ROTA_ENTREGA(2, "Em Rota de Entrega"),
    ENTREGUE(3, "Entregue");

    private final int id;
    private final String descricao;

    StatusEntregaEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}