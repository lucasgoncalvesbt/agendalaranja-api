package br.com.fcamara.hackatonapi.exception;

public class SchedulingExceededException extends RuntimeException{

    public SchedulingExceededException(Long id) {
        super(String.format("A quantidade máxima de agendamentos da estacao %s ja foi alcaçada!", id));
    }

}
