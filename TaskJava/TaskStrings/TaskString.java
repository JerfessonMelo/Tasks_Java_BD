package TaskStrings;

public class TaskString {

    public static void main(String[] args) {


        String gasolina = "TaSemGasolina";
        int stringLength = gasolina.length();
        System.out.println("Quantidade de Caracteres: "+stringLength);
        String nomeMaiusculo = gasolina.toUpperCase();
        System.out.println("Nome em Maiusculo: "+nomeMaiusculo);

        int vogais = 0;
        String listaVogais = "aeiouAEIOU";
        for(int i=0; i <gasolina.length(); i++){
            char letra = gasolina.charAt(i);
            if (listaVogais.indexOf(letra)!=-1) {
                vogais++;
            }
        }
        gasolina = gasolina.toLowerCase();  
        System.out.println("Quantidade de Vogais: "+vogais);
        System.out.println("O Nome começa com UNI: " + gasolina.startsWith("uni"));
        System.out.println("O Nome termina com Rio: " + gasolina.endsWith("rio"));


    }
}
