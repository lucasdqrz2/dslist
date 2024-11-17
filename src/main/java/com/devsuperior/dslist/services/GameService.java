package com.devsuperior.dslist.services;

import com.devsuperior.dslist.GameMinDTO.DTO;
import com.devsuperior.dslist.GameMinDTO.GameDTO;
import com.devsuperior.dslist.entities.Game;
import com.devsuperior.dslist.projection.GameMinProjection;
import com.devsuperior.dslist.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameDTO findByID(Long id) {
        Game result = gameRepository.findById(id).get();
         return new GameDTO(result);
    }

    @Transactional(readOnly = true)
    public List<DTO> findAll() {
        List<Game> result = gameRepository.findAll();
        return result.stream().map(x -> new DTO(x)).toList();
    }

    @Transactional(readOnly = true)
    public List<DTO> findByList(Long listId) {
        List<GameMinProjection> result = gameRepository.searchByList(listId);
        return result.stream().map(x -> new DTO(x)).toList();
    }
}
