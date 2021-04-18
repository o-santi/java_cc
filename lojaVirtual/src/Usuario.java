public class Usuario {

    private String nome;

    private long cpf;

    private String endereco;

    public Usuario(String nome, long cpf, String endereco) {
	this.nome = nome;
	this.cpf = cpf;
	this.endereco = endereco;
    }

    public String getNome() {
	return this.nome;
    }
	
    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Usuario other = (Usuario) obj;
	if (cpf == other.cpf)
	    return true;
	return false;
	}
}
