package com.bol.mancala.domain.dto.Game.player;

import com.bol.mancala.base.config.oop.IValidation;
import com.bol.mancala.base.exception.CustomErrorCode;
import com.bol.mancala.base.exception.CustomException;
import com.bol.mancala.domain.model.player.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * A Request mediator DTO for gameSetup creation for players
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayerDto implements IValidation {

    private String userName;

    public PlayerDto(Player player) {
        if (player != null) {
            this.userName = player.getUserName();
        }
    }


    @Override
    public void validate() throws CustomException {
        if (StringUtils.isEmpty(userName)) {
            throw new CustomException(CustomErrorCode.VALIDATION_FAILED, "username is empty");
        }

    }
}
