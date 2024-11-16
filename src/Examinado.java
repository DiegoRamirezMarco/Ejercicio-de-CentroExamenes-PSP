/*La clase Examinado se lanza cada vez que
un alumno va a examinarse, simulando la realización
del examen por parte del alumno. Un ejemplo de salida
de examen podría ser:
E2-2024;Miguel; Pregunta 1;C
E2-2024;Miguel; Pregunta 2;-
E2-2024;Miguel; Pregunta 3;A
E2-2024;Miguel; Pregunta 4;C
E2-2024;Miguel; Pregunta 5;D
E2-2024;Miguel; Pregunta 6;-
E2-2024;Miguel; Pregunta 7;-
E2-2024;Miguel; Pregunta 8;B
E2-2024;Miguel; Pregunta 9;D
E2-2024;Miguel; Pregunta 10;-
*/


/*Esta salida en pantalla representa la realización del examen
con código E2-2024, que corresponde al alumno Miguel.
El examen consta de 10 preguntas, cuyas respuestas
se han seleccionado al azar en los valores A, B, C, D
o guión (sin responder).

Puesto que varios alumnos se examinan simultáneamente,
podrán entremezclarse líneas de la respuesta del examen
de un alumno con las del examen de otro. Pero las respuestas
del examen de un mismo alumno siempre tendrán el mismo código.
*/

import java.util.Random;

public class Examinado implements Runnable {
    private Thread hilo;
    BufferExamenes buffer;
    public Thread getHilo() {
        return hilo;
    }
    public Examinado(String alumno, BufferExamenes generador) {
// Construye el hilo. El nombre será el nombre del alumno.
// Establece el valor de la propiedad buffer
// Inicia el hilo.
        this.hilo = new Thread(this,alumno);
        this.buffer = generador;
        hilo.start();
    }
    @Override
    public synchronized void run() {
        String codigoExamen = this.buffer.consumirExamen();
        if (codigoExamen != null) {
// Simula aquí un examen de 10 preguntas
// cuyas respuestas se seleccionarán al azar
// entre A, B, C, D o – (sin contestar).
            char[] respuestas = {'A','B','C','D','-'};
            Random r = new Random();
            for(int i = 1 ; i <= 10 ; ++i){
                int nR = r.nextInt(respuestas.length);
                char letra = respuestas[nR];
                System.out.println(codigoExamen + "; "+hilo.getName()+"; Pregunta "+i +"; " + letra );
            }
        }
        else {
            System.out.println("Agotado tiempo de espera y " +
                    "no hay más exámenes");
        }
    }
}

