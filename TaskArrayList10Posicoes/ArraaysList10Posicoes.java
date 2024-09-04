package TaskArrayList10Posicoes;

import java.util.ArrayList;

public class ArraaysList10Posicoes {

    public static void main(String[] args) {
    ArrayList<String> nomesArray = new ArrayList<>();
    nomesArray.add("Talles");
    nomesArray.add("Italo");
    nomesArray.add("Junior");
    nomesArray.add("Andre");
    nomesArray.add("Pedro");
    nomesArray.add("Edilson");
    nomesArray.add("Augusto");
    nomesArray.add("Emanuel");
    nomesArray.add("Paulo");
    nomesArray.add("Silmara");

    for(int i = 3; i < nomesArray.size(); i++ ){
        System.out.println(i + "=> " + nomesArray.get(i));
        }
    }
}
