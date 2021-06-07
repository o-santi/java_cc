package modelo;

public class Usuario {

    private String nome;

    private long cpf;
    
    private String endereco;

    public Usuario(String nome, long cpf) {
	this.nome = nome;
	this.cpf = cpf;
    }

    public long getCpf() {
        return this.cpf;  // ToDo IMPLEMENT ME!!!
    }

    public String getNome() {
        return this.nome;  // ToDo IMPLEMENT ME!!!
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getEndereco() {
        return this.endereco; // ToDo IMPLEMENT ME!!!
    }

    public void setEndereco(String endereco) {
	this.endereco = endereco;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (int) (cpf ^ (cpf >>> 32));
	return result;
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
	if (cpf != other.cpf)
	    return false;
	return true;
    }
}
