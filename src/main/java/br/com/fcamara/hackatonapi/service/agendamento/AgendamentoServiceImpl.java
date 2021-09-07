package br.com.fcamara.hackatonapi.service.agendamento;

import br.com.fcamara.hackatonapi.dto.AgendamentoDTO;
import br.com.fcamara.hackatonapi.exception.EmailAlreadyScheduledException;
import br.com.fcamara.hackatonapi.exception.NotFoundException;
import br.com.fcamara.hackatonapi.exception.SchedulingExceededException;
import br.com.fcamara.hackatonapi.model.Agendamento;
import br.com.fcamara.hackatonapi.model.Estacao;
import br.com.fcamara.hackatonapi.repository.AgendamentoRepository;
import br.com.fcamara.hackatonapi.repository.EstacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AgendamentoServiceImpl implements AgendamentoService{

    private AgendamentoRepository agendamentoRepository;
    private EstacaoRepository  estacaoRepository;

    @Autowired
    public AgendamentoServiceImpl(AgendamentoRepository agendamentoRepository, EstacaoRepository  estacaoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.estacaoRepository = estacaoRepository;
    }



    @Override
    public List<Agendamento> getAllScheduling(Long estacaoId) {

        return agendamentoRepository.findByEstacao_Id(estacaoId);
    }

    @Override
    public Agendamento getSchedulingById(UUID id) {
        return agendamentoRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString(), "Agendamento"));
    }

    @Override
    public Agendamento createScheduling(AgendamentoDTO agendamentoDTO) {
        Estacao estacao = estacaoRepository.findById(agendamentoDTO.getEstacaoId())
                .orElseThrow(() -> new NotFoundException(agendamentoDTO.getEstacaoId().toString(), "Estacao"));


        List<Agendamento> agendamentos = agendamentoRepository.findByDataAgendada(agendamentoDTO.getDataAgendada());

        if (agendamentos.stream().anyMatch(a -> a.getEmailConsultor().equals(agendamentoDTO.getEmailConsultor()))) {
            throw new EmailAlreadyScheduledException(agendamentoDTO.getEmailConsultor());
        }

        verificaSeONumeroDeAgendamentosJaEstaNoMaximo(agendamentoDTO, estacao, agendamentos);

        Agendamento agendamento = Agendamento.builder()
                .nomeConsultor(agendamentoDTO.getNomeConsultor())
                .emailConsultor(agendamentoDTO.getEmailConsultor())
                .estacao(estacao)
                .dataAgendada(agendamentoDTO.getDataAgendada())
                .build();

        return agendamentoRepository.save(agendamento);
    }


    @Override
    public Agendamento updateScheduling(UUID id, AgendamentoDTO agendamentoDTO) {
        Agendamento agendamento = getSchedulingById(id);
        Estacao estacao = estacaoRepository.findById(agendamentoDTO.getEstacaoId())
                .orElseThrow(() -> new NotFoundException(agendamentoDTO.getEstacaoId().toString(), "Estação"));

        List<Agendamento> agendamentos = agendamentoRepository.findByDataAgendada(agendamentoDTO.getDataAgendada());

        if (agendamento.getDataAgendada() != agendamentoDTO.getDataAgendada() || agendamento.getEstacao().getId() != agendamentoDTO.getEstacaoId()) {
            verificaSeONumeroDeAgendamentosJaEstaNoMaximo(agendamentoDTO, estacao, agendamentos);
        }

        agendamento.setDataAgendada(agendamentoDTO.getDataAgendada());
        agendamento.setEstacao(estacao);

        return agendamentoRepository.save(agendamento);
    }

    @Override
    public void deleteScheduling(UUID id) {
        getSchedulingById(id);
        agendamentoRepository.deleteById(id);
    }


    private void verificaSeONumeroDeAgendamentosJaEstaNoMaximo(AgendamentoDTO agendamentoDTO, Estacao estacao, List<Agendamento> agendamentos) {
        Integer somaDosAgendametosNoDia = verificaNaEstacaoNumeroDeAgendamentosNoDia(agendamentoDTO, agendamentos);
        Double qtdMaxDeLugaresNaEstacao = estacao.getQtdLugares();
        if (somaDosAgendametosNoDia + 1 > qtdMaxDeLugaresNaEstacao) {
            throw new SchedulingExceededException(estacao.getId());
        }
    }

    private Integer verificaNaEstacaoNumeroDeAgendamentosNoDia(AgendamentoDTO agendamentoDTO, List<Agendamento> agendamentos) {

        Integer somaDosAgendametosNoDia = (int) agendamentos.stream()
                .filter(agendamento -> agendamento.getEstacao().getId().equals(agendamentoDTO.getEstacaoId())).count();

        return somaDosAgendametosNoDia;
    }
}
