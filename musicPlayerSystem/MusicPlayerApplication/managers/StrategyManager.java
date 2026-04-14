package musicPlayerSystem.MusicPlayerApplication.managers;

import musicPlayerSystem.MusicPlayerApplication.enums.PlayStrategyType;
import musicPlayerSystem.MusicPlayerApplication.factories.StrategyFactory;
import musicPlayerSystem.MusicPlayerApplication.strategies.IPlayStrategy;

public class StrategyManager {
    
    private static StrategyManager instance = null;

    public static StrategyManager getInstance() {
        if (instance == null) {
            synchronized (StrategyManager.class) {
                if (instance == null) {
                    instance = new StrategyManager();
                }
            }
        }
        return instance;
    }

    public IPlayStrategy getCurrentStrategy(PlayStrategyType strategyType) {
        return StrategyFactory.createStrategy(strategyType); 
    }
}
