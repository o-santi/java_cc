/**
 * Representa uma fração de forma explícita, guardando numerador e denominador inteiros.
 * Frações equivalentes (matematicamente) devem ser consideradas equals().
 */

public class Fracao {

    /**
     * Cria uma fração, baseada em seu numerador e denominador.
     * O sinal da fração será inferido a partir do sinal
     * do numerador e do denominador.
     *
     * @param numerador o numerador
     * @param denominador o denominador
     */

    public int denominador;

    public int numerador;

    public boolean sinal;
    
    public Fracao(int numerador, int denominador) {

        if (denominador == 0) {
            throw new ArithmeticException("Denominador não pode ser zero!!");
        }

	this.numerador = numerador;
	this.denominador = denominador;

	this.sinal = this.getSinal();
    }

    /**
     * Retorna o sinal da fração.
     *
     * @return true, se for positiva ou nula (zero);
     *         false, se for negativa.
     */
    public boolean getSinal() {
	return this.numerador * this.denominador >= 0 ? true : false;
    }

    /**
     * Retorna o (valor absoluto do) numerador desta fração, ou seja, sempre não-negativo
     * @return o numerador
     */
    public int getNumerador() {
	return AritmeticaUtils.absoluteVal(this.numerador);
    }

    /**
     * Retorna o (valor absoluto do) denominador desta fração, ou seja, sempre positivo
     * @return o numerador
     */
    public int getDenominador() {
	return AritmeticaUtils.absoluteVal(this.denominador);
    }

    /**
     * Retorna o valor numérico da fração (com o sinal correto).
     *
     * @return um float, com o valor na melhor precisão possível
     *         Ex.: -1/3 vai retornar 0.33333333
     */
    public float getValorNumerico() {
	return ((float) this.numerador) / this.denominador;
    }

    /**
     * Retorna uma fração equivalente à esta fração,
     * e que não pode mais ser simplificada (irredutível).
     *
     * @return um outro objeto Fracao, se esta fração for redutível;
     *         esta própria fração (this), se ela já for irredutível
     */
    public Fracao getFracaoGeratriz() {
	int fracaoMdc = AritmeticaUtils.mdc(AritmeticaUtils.absoluteVal(numerador), AritmeticaUtils.absoluteVal(denominador));
	if (fracaoMdc == 1) {
	    return this;
	}
	int numeradorReduzido = numerador / fracaoMdc;
	int denominadorReduzido = denominador / fracaoMdc;
	return new Fracao(numeradorReduzido, denominadorReduzido);
    }

    @Override
    public String toString() {
	if (this.numerador == 0) {
	    return "0";
	}
	int num = this.getNumerador();
	int den = this.getDenominador();

	String returnString = "";

	if (!(this.sinal)) {
	    returnString += "-";
	}
	returnString += String.format("%d", num);

	if (den != 1) {
	    returnString += String.format("/%d", den);
	}

	return returnString;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Fracao other = (Fracao) obj;
	Fracao ger1 = this.getFracaoGeratriz();
	Fracao ger2 = other.getFracaoGeratriz();
	if (ger1.getDenominador() == ger2.getDenominador() && ger1.getNumerador() == ger2.getNumerador()) 
	    return true;
	return false;
    }

}
