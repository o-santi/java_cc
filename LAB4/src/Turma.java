import java.util.HashMap;

public class Turma {

    //private HashMap<Nome, Professor> professores = new HashMap<>(); // professores tem dre? não sei.

    private HashMap<Integer, Aluno> alunos = new HashMap<>(); // alunos tem!

    private HashMap<Aluno, Float> notas = new HashMap<>();

    public void inscreverAluno(Aluno aluno) {
	if (!alunos.containsValue(aluno.getDre())) {
	    this.alunos.put(aluno.getDre(), aluno);
	}
    }

    public void atribuirMediaFinal(long dre, float nota) {
	alunos.put(alunos.get(dre), nota);
    }

    public float obterMediaFinal(long dre) {
	return notas.get(alunos.get(dre)); 
    }
    
    public String listarAlunos() {
	String lista = "";
	for (Aluno aluno : alunos.values()) {
	    lista += aluno.toString() + "\n";
	}
	return lista;
    }
}
