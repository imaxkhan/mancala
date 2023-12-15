package com.bol.mancala.game.rule.basic.container;

import com.bol.mancala.domain.model.concept.Store;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StoreContainer {
    private Store ownStore;
    private List<Store> opponentStores;

    public StoreContainer(Store ownStore, List<Store> opponentStores) {
        this.ownStore = ownStore;
        this.opponentStores = opponentStores;
    }
}
