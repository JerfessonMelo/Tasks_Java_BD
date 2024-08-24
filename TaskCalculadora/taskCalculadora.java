package TaskCalculadora;
public class taskCalculadora {
        public static void main(String[] args) {
        Calculadora calculo = new Calculadora();

        calculo.somar(15, 20);
        double resultadoSomar = calculo.resultadoSomar;
        System.out.println("Resultado da Somar: "+resultadoSomar);
        
        calculo.subtracao(10, 2);
        double resultadoSubtracao = calculo.resultadoSubtracao;
        System.out.println("Resultado da Subtração: "+resultadoSubtracao);
    
        calculo.divisao(15, 3);
        double resultadoDivisao = calculo.resultadoDivisao;
        System.out.println("Resultado da Divisão: "+resultadoDivisao);

        calculo.multiplica(15, 3);
        double resultadoMultiplica = calculo.resultadoMultiplica;
        System.out.println("Resultado da Multiplicação: "+resultadoMultiplica);    
    }

}
