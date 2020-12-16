package view.debugger;

/*
 setzen Sie den Haltepunkt genau vor die Anweisung die zum ersten Mal die fehlerhafte Ausgabe produziert
 */
public class Counter {
    /**
     * gibt die ganzen Zahlen zwischen min und max auf der Konsole aus (einschliesslich min und max)
     *
     * @param min untere Grenze
     * @param max obere Grenze
     */
    public void count(Float min, Float max) {
        Float last = null;
        for (float i = min; i <= max; i++) {
            System.out.println(i);
            last = i;
        }
    }
}
