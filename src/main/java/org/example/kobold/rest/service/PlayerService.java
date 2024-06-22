package org.example.kobold.rest.service;

import org.example.kobold.rest.CRUDInterface;
import org.example.kobold.rest.models.Player;
import org.example.kobold.rest.repositories.PlayerRepository;

public class PlayerService implements CRUDInterface<Player> {

    PlayerRepository playerRepository = new PlayerRepository();

    @Override
    public Player save(Player entity) {
        return playerRepository.save(entity);
    }

    @Override
    public int deleteById(int id) {
        return playerRepository.deleteById(id);
    }

    @Override
    public Player updateById(Player entity, int id) {
        return playerRepository.updateById(entity, id);
    }

    @Override
    public Player getById(int id) {
        return playerRepository.getById(id);
    }
}
