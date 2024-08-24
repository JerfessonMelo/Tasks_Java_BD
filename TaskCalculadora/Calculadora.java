package TaskCalculadora;
public class Calculadora {

    double resultadoSomar;
    double resultadoSubtracao;
    double resultadoDivisao;
    double resultadoMultiplica;
    
    void somar(double valorUm, double valorDois) {
        resultadoSomar = valorUm + valorDois;
    }

    void subtracao(double valorUm, double valorDois) {
        resultadoSubtracao = valorUm - valorDois;
    }

    void divisao(double valorUm, double valorDois) {
        resultadoDivisao = valorUm / valorDois;
    }

    void multiplica(double valorUm, double valorDois){
        resultadoMultiplica = valorUm * valorDois;
    }

}