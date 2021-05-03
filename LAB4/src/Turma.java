import java.util.HashMap;

public class Turma {

    //private HashMap<Nome, Professor> professores = new HashMap<>(); // professores tem dre? não sei.

    private HashMap<Long, Aluno> alunos = new HashMap<>(); // alunos tem!

    private HashMap<Long, Float> notas = new HashMap<>();

    public void inscreverAluno(Aluno aluno) {
	if (!alunos.containsValue(aluno.getDre())) {
	    this.alunos.put(aluno.getDre(), aluno);
	}
    }

    public void atribuirMediaFinal(long dre, float nota) {
	notas.put(dre, nota);
    }

    public float obterMediaFinal(long dre) {
	return notas.get(dre); 
    }
    
    public String listarAlunos() {
	String lista = "";
	for (Aluno aluno : alunos.values()) {
	    lista += aluno.toString() + " " + notas.get(aluno.getDre()) + "\n";
	}
	return lista;
    }
}
