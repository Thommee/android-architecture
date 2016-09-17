package pl.training.githubbrowser.flux;

public interface StoreAction {

    Object getData();
    ActionType getActionType();
}
