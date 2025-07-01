public class EscenarioNode {
    private String descripcion;
    private EscenarioNode izquierda;
    private EscenarioNode derecha;

    public EscenarioNode(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public EscenarioNode getIzquierda() {
        return izquierda;
    }

    public EscenarioNode getDerecha() {
        return derecha;
    }

    public void setIzquierda(EscenarioNode izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(EscenarioNode derecha) {
        this.derecha = derecha;
    }
}