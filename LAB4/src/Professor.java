public class Professor extends Pessoa {
    
    private final int anoContratacao;
    
    public int getAnoContatacao() {
	return anoContratacao;
    }

    public Professor(int anoNascimento, String nome, int anoContratacao) {
	super(anoNascimento, nome);
	this.anoContratacao = anoContratacao;
    }
}
