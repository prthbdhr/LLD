package musicPlayerSystem.MusicPlayerApplication.factories;

import musicPlayerSystem.MusicPlayerApplication.enums.PlayStrategyType;
import musicPlayerSystem.MusicPlayerApplication.strategies.CustomQueueStrategy;
import musicPlayerSystem.MusicPlayerApplication.strategies.IPlayStrategy;
import musicPlayerSystem.MusicPlayerApplication.strategies.RandomPlayStrategy;
import musicPlayerSystem.MusicPlayerApplication.strategies.SequentialPlayStrategy;

public class StrategyFactory {
        public static IPlayStrategy createStrategy(PlayStrategyType strategyType) {
            switch (strategyType) {
                case SEQUENTIAL:
                    return new SequentialPlayStrategy();
                case RANDOM:
                    return new RandomPlayStrategy();
                case CUSTOM_QUEUE:
                    return new CustomQueueStrategy();
                default:
                    return new SequentialPlayStrategy();
            }
        }
}
