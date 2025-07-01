public class ArbolEscenarios {
    private EscenarioNode raiz;

    public ArbolEscenarios() {
        construirArbol();
    }

    public EscenarioNode getRaiz() {
        return raiz;
    }

    private void construirArbol() {
        raiz = new EscenarioNode("Inicio del entrenamiento");

        // Escenario 1

        // Nivel 1
        EscenarioNode falloTraje = new EscenarioNode("Falla en el traje espacial");
        raiz.setIzquierda(falloTraje);

        // Nivel 2
        EscenarioNode reparar = new EscenarioNode("Reparar con kit de emergencia");
        EscenarioNode volver = new EscenarioNode("Volver a la nave");
        falloTraje.setIzquierda(reparar);
        falloTraje.setDerecha(volver);

        // Nivel 3 - Reparar con kit (kit dañado)
        EscenarioNode improvisar = new EscenarioNode("Intentar improvisar reparación");
        EscenarioNode pedirAyuda = new EscenarioNode("Pedir ayuda al compañero");
        reparar.setIzquierda(improvisar);
        reparar.setDerecha(pedirAyuda);

        // Nivel 4 - Resultados de reparación
        improvisar.setIzquierda(new EscenarioNode("Resultado: Reparación parcial. Puedes continuar, pero con precaución."));
        pedirAyuda.setIzquierda(new EscenarioNode("Resultado: El compañero reparó tu traje. Puedes seguir con la misión."));

        // Nivel 3 - Volver a la nave (obstáculo inesperado)
        EscenarioNode rodear = new EscenarioNode("Intentar rodear el obstáculo");
        EscenarioNode pedirRescate = new EscenarioNode("Pedir rescate a control");
        volver.setIzquierda(rodear);
        volver.setDerecha(pedirRescate);

        // Nivel 4 - Resultados de volver
        rodear.setIzquierda(new EscenarioNode("Resultado: Lograste volver, pero con retraso y pérdida de energía."));
        pedirRescate.setIzquierda(new EscenarioNode("Resultado: Control te rescata. La misión se reprogramará."));

        // Escenario 2
        // Nivel 1
        EscenarioNode tormenta = new EscenarioNode("Se aproxima una tormenta de micrometeoritos");
        raiz.setDerecha(tormenta);

        // Nivel 2
        EscenarioNode buscarRefugio = new EscenarioNode("Buscar refugio en una cueva");
        EscenarioNode continuarMision = new EscenarioNode("Continuar la misión afrontando la tormenta");
        tormenta.setIzquierda(buscarRefugio);
        tormenta.setDerecha(continuarMision);

        // Ruta 1

        // Nivel 3
        EscenarioNode cuevaInestable = new EscenarioNode("Entrar a una cueva aparentemente inestable pero cercana");
        EscenarioNode cuevaSegura = new EscenarioNode("Ir a una cueva más lejana pero estable");
        buscarRefugio.setIzquierda(cuevaInestable);
        buscarRefugio.setDerecha(cuevaSegura);
        // Nivel 4
        cuevaInestable.setIzquierda(new EscenarioNode("Resultado: La entrada de la cueva colapsa. Quedas atrapado y debes ser rescatado."));
        cuevaSegura.setIzquierda(new EscenarioNode("Resultado: Esperas de forma segura a que pase la tormenta. Continuas la misión con retraso."));

        // Ruta 2

        // Nivel 3
        EscenarioNode usarPropulsores = new EscenarioNode("Usar propulsores para realizar maniobras evasivas");
        EscenarioNode usarEscudo = new EscenarioNode("Usar una roca grande como escudo y avanzar lentamente");
        continuarMision.setIzquierda(usarPropulsores);
        continuarMision.setDerecha(usarEscudo);

        // Nivel 4
        usarPropulsores.setIzquierda(new EscenarioNode("Resultado: Esquivas impactos críticos, pero el alto consumo de combustible del propulsor pone en riesgo el resto de la misión."));
        usarEscudo.setIzquierda(new EscenarioNode("Resultado: La roca te protege, pero avanzas muy lento. El equipo de recolección sufre daños menores."));
    }
}