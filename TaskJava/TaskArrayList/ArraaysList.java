package TaskArrayList;

import java.util.ArrayList;

public class ArraaysList {

        public static void main(String[] args) {
        
        ArrayList<String> nomesArray = new ArrayList<>();
        nomesArray.add("Rony Weasly");
        nomesArray.add("Harry Potter");
        nomesArray.add("Hermione Granger");
        nomesArray.add("Severo Snape");
        nomesArray.add("Albus Dumbledore");
        nomesArray.remove(1);
        for(int i = 0; i < nomesArray.size(); i++ ){
        System.out.println(i + " => " + nomesArray.get(i));
        }
        nomesArray.remove(2);
        for(int i = 0; i < nomesArray.size(); i++){
            System.out.println("Imprimindo os Valores do Array: " + i);               
            }
                if (nomesArray.get(0).equals("Talles")) {
                System.out.println("Talles esta na Primeira Posição");
                }
                else{
                System.out.println("Talles Não esta na Primeira Posição");
            }
    }
}
