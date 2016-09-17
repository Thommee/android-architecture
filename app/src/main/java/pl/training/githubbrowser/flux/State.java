package pl.training.githubbrowser.flux;

import java.util.ArrayList;
import java.util.List;

import pl.training.githubbrowser.viewmodel.RepositoryViewModel;

public class State {

    private List<RepositoryViewModel> list = new ArrayList<>();

    public State(List<RepositoryViewModel> list) {
        this.list = list;
    }

    public List<RepositoryViewModel> getList() {
        return list;
    }
}
