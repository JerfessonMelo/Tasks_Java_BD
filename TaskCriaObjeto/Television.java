package TaskCriaObjeto;
public class Television {

    public static void main(String[] args) {
        Televisao tele = new Televisao();
        tele.marcaTelevisao = "Samsung";
        tele.polegadasTelevisao = 55;
        tele.quantidadeCanal = 15;
        tele.voltagemTelevisao = 220;
        tele.isNova = true;
        tele.isSmartTv = true;

        tele.detalhesTelevisao();
        tele.aumentaQuantidadeCanal(20);
        System.out.println("Nova Quantidade de Canal: "+ tele.quantidadeCanal);
        System.out.println("Televisão e Nova? "+ tele.isNova);
        tele.desligarTv();
        System.out.println("Televisão esta Desligada? " + tele.desligarTv());
        System.out.println("Televisão esta Ligada? " + tele.ligarTv());
        tele.trocarCanal();
    }

}
