package pl.training.githubbrowser.flux;

import java.util.List;

import pl.training.githubbrowser.viewmodel.RepositoryViewModel;

public class RepositoryChangeAction implements StoreAction {


    public static final ActionType ACTION_TYPE = ActionType.REPOSITORY_CHANGE;
    private List<RepositoryViewModel> list;

    public RepositoryChangeAction(List<RepositoryViewModel> list) {
        this.list = list;
    }

    @Override
    public List<RepositoryViewModel> getData() {
        return list;
    }

    @Override
    public ActionType getActionType() {
        return ACTION_TYPE;
    }
}
