/*La clase ProductorExamenes se encargará de
generar exámenes, asignándole a cada uno un código que
estará formado por la letra E seguida del número de examen,
un guión y el año, por ejemplo: “E2-2024
(segundo examen del año 2024).” El número de examen se
establece a partir de la variable estática numeroExamen.
 */
import java.time.LocalDateTime;

public class ProductorExamenes implements Runnable {
    private BufferExamenes buffer;
    private static int numeroExamen = 0;
    private Thread hilo;

    public ProductorExamenes(BufferExamenes buffer) {
// Incrementa el contador de exámenes (variable numeroExamen).
// Construye el hilo. El nombre será la letra E seguida
// del valor de la variable numeroExamen.
// Establece el valor de la propiedad buffer
// Inicia el hilo.
        ++numeroExamen;
        this.hilo = new Thread(this,"E"+numeroExamen);
        this.buffer = buffer;
        hilo.start();
    }

    @Override
    public void run() {
        int aa = LocalDateTime.now().getYear();
        String codigo = this.hilo.getName() + "-" +aa;
// Añade el nuevo código al buffer de exámenes.
// Muestra un mensaje en consola informando sobre el
// código del examen que se acaba de producir.
        buffer.fabricarNuevoExamen(codigo);
    }

}