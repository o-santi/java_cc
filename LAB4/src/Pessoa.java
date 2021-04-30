public class Pessoa {
    
    private int anoNascimento;

    private String nome;

    public String getNome() {
        return nome;
    }

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public Pessoa(int anoNascimento, String nome) {
	this.anoNascimento = anoNascimento;
	this.nome = nome;
    }
}
