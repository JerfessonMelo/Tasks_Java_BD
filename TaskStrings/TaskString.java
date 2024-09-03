package TaskStrings;

public class TaskString {

    public static void main(String[] args) {


        String gasolina = "TaSemGasolina";
        int stringLength = gasolina.length();
        System.out.println(stringLength);
        String nomeMinusculo = gasolina.toLowerCase();
        System.out.println(nomeMinusculo);
        String nomeMaiusculo = gasolina.toUpperCase();
        System.out.println(nomeMaiusculo);

        int vogais = 0;
        String listaVogais = "aeiouAEIOU";
        for(int i=0; i <gasolina.length(); i++){
            char letra = gasolina.charAt(i);
            if (listaVogais.indexOf(letra)!=-1) {
                vogais++;
            }
        }
             
        System.out.println(vogais);


    }
}
