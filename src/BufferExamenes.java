/*Para cada alumno que va a examinarse se debe
 imprimir un examen, que tendrá un código diferenciado.
 La clase BufferExamenes mantiene una cola (objeto LinkedList)
 de códigos de examen. Para cada alumno se extrae un
 examen de la cola.
 */
import java.util.LinkedList;
import java.util.Queue;

public class BufferExamenes {
    private Queue<String> colaExamenes;

    public BufferExamenes() {
        colaExamenes = new LinkedList<String>();
    }

    public synchronized void fabricarNuevoExamen(String codigo) {
// Aquí se fabrica un nuevo examen.
// Un hilo de la clase ProductorExamenes
// se encargará de fabricarlo
// y pasarlo como argumento a este métod.
// Añade el código pasado como argumento a la cola
// y libera el hilo que está intentando consumir
// un nuevo examen.
    colaExamenes.add(codigo);
        System.out.println("Examen creado: " + codigo);
        notify();
    }
    public synchronized String consumirExamen() {
// Este métod se encargará de suministrar un examen
// a cada hilo de tipo Examinador que lo solicite.
// Para suministrar el examen habrá antes que esperar
// hasta que haya algún examen para consumir en la cola.
// Haz aquí una pausa hasta que se haya fabricado algún examen.
// Si la cola sigue sin estar vacía, saca un examen y
// entrégalo como retorno de esta función.
        while (colaExamenes.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Se interrumpio la espera");
                //Uso esto por si al hilo falla en vez de borrarse lo restaura
                Thread.currentThread().interrupt();
            }
        }
        //Elimino el primer examen que entro y lo devuelvo como String
        return colaExamenes.poll();
    }

}