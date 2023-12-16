package com.bol.mancala.game.service.impl;

import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.game.repository.GameRepository;
import com.bol.mancala.game.rule.basic.GameCommanderChain;
import com.bol.mancala.game.service.GamePlayService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class GamePlayServiceImplTest {

    @Mock
    private GameRepository gameRepository;
    @InjectMocks
    private GamePlayServiceImpl gamePlayService;
    @Spy
    private GameCommanderChain commanderChain;

    @Test
    public void testPlayWhenGameDoesNotExist() {
        when(gameRepository.findByGameId(any())).thenReturn(Optional.empty());
        assertThrows(CustomException.class, () -> gamePlayService.play(UUID.randomUUID(), any()));
    }

}