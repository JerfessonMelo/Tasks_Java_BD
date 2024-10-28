package TaskCriaObjeto;
public class Televisao {
    
    String marcaTelevisao;
    int polegadasTelevisao;
    int voltagemTelevisao;
    int quantidadeCanal;
    boolean isSmartTv;
    boolean isNova;
    boolean isLigada;
    boolean isDesligada;
    


// Função Para Saber se e Nova
    boolean televisaoNova(){
        return isNova;
    }
// Função Para Aumenta a Quantidade de Canal
    void aumentaQuantidadeCanal(int novaQunatidadeCanal){
        quantidadeCanal = quantidadeCanal + novaQunatidadeCanal;
    }
// Função Para Ver Detalhes da Tv
    void detalhesTelevisao(){
        System.out.println("Marca Da Televisão: " + marcaTelevisao);
        System.out.println("E SmartTv: " + isSmartTv);
        System.out.println("Tamanho Da Televisão: " + polegadasTelevisao);
        System.out.println("Voltagem Da Televisão: " + voltagemTelevisao);
        System.out.println("Quantidade De Canal Televisão: " + quantidadeCanal);
    }
// Função Para Mostra que a Tv esta Desligada
    boolean desligarTv(){
        return isDesligada;
    }
// Função Para Mostra que a Tv Esta Ligada
    boolean ligarTv(){
        isLigada = true;
       return isLigada;
    }

// Função Para Trocar de Canal
    void trocarCanal(){
        System.out.println("Trocando de Canal");
    }
}
