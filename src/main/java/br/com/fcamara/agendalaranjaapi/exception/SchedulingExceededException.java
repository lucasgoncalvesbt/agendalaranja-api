package br.com.fcamara.agendalaranjaapi.exception;

public class SchedulingExceededException extends RuntimeException{

    public SchedulingExceededException(String nomeEstacao) {
        super(String.format("A quantidade máxima de agendamentos da estacao %s ja foi alcaçada!", nomeEstacao));
    }

}
