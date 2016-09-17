package pl.training.githubbrowser.flux;

import android.util.Log;

import java.util.Collections;
import java.util.List;

import pl.training.githubbrowser.viewmodel.RepositoryViewModel;
import rx.subjects.PublishSubject;

public class Store {

    private List<Reducer> reducers = Collections.singletonList(new ListReducer());

    private State state = new State(Collections.emptyList());
    public PublishSubject<StoreChangeEvent> eventsStream = PublishSubject.create();


    public void onAction(StoreAction action) {

        for (Reducer reducer : reducers) {
            state = reducer.reduce(state, action);
        }

        eventsStream.onNext(new StoreChangeEvent());
        Log.d("STORE", "change list " + state.getList().size());
    }

    public List<RepositoryViewModel> getList() {
        return state.getList();
    }
}
