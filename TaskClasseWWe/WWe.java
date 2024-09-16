package TaskClasseWWe;

public class WWe {

    double level;
	double forca;
	double velocidade;
	double vida;
	double azar;
	double resistencia;
	double astucia;
	double Defesa;
    double vidaExtra;
    double sorte;
	
	public WWe(double level) {
		this.level = level;
	}
	
	public WWe(double forca, double velocidade, double astucia) {
		this.forca = forca;
		this.velocidade = velocidade;
		this.astucia = astucia;
		
	}
	
	public WWe(double vida, double azar) {
		this.vida = vida;
		this.azar = azar;
		
	}
	
	void VerificarOponente() {
		astucia++;
	}
	void treino() {
		forca++;
	}
	void Defende() {
		Defesa++;
	}
	void avanca() {
		velocidade++;
	}
	void Ataque() {
		vida++;
	}
	void subirLevel() {
		level++;
	}
	void descanso() {
		resistencia++;
	}

    void deSorteAoAzar(){
        azar--;
    }

    void Vidaextras(){
        sorte++;
    }

}
