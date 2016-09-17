package pl.training.githubbrowser.flux;

public interface Reducer {

    State reduce(State state, StoreAction action);
}
