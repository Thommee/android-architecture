package pl.training.githubbrowser.flux;

public class ListReducer implements Reducer {

    public State reduce(State state, StoreAction action) {
        return action.getActionType() == ActionType.REPOSITORY_CHANGE
                ? new State(((RepositoryChangeAction) action).getData())
                : state;
    }
}
