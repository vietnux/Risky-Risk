package no.ntnu.idi.tdt4240.Models;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;

import no.ntnu.idi.tdt4240.Components.TeamComponent;
import no.ntnu.idi.tdt4240.EntitySystems.BoardSystem;
import no.ntnu.idi.tdt4240.EntitySystems.PlayerSystem;
import no.ntnu.idi.tdt4240.EntitySystems.RenderSystem;
import no.ntnu.idi.tdt4240.EntitySystems.TeamSystem;
import no.ntnu.idi.tdt4240.TagComponents.Player;

/**
 * Created by Oivind on 3/21/2019.
 */

public class GameModel {

    private Engine engine;
    public Engine getEngine() {
        return engine;
    }
    public GameSettings gameSettings;

    public GameModel() {
        engine = new Engine();
        RenderSystem rs = new RenderSystem();
        BoardSystem bs = new BoardSystem();

        engine.addSystem(rs);
        engine.addSystem(bs);

        gameSettings = new GameSettings();
    }

    public void setup() {
        reset();

        // Setup players
        engine.addSystem(new TeamSystem());
        for(int i = 0; i < gameSettings.numberOfPlayers; i++) {
            Entity team = new Entity();
            team.add(new TeamComponent());
            team.add(new Player());
            engine.addEntity(team);
        }
    }

    private void reset() {
        engine.removeAllEntities();
    }

    public class GameSettings{
        private int numberOfPlayers;
        public int getNumberOfPlayers() {return numberOfPlayers;}
        public void setNumberOfPlayers(int num) {
            if(num > 6) {
                numberOfPlayers = 6;
            } else if(num < 2) {
                numberOfPlayers = 2;
            } else {
                numberOfPlayers = num;
            }
        }
    }
}
