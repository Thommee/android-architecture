package pl.training.githubbrowser.flux;

import android.util.Log;

import java.util.List;

import pl.training.githubbrowser.viewmodel.RepositoryViewModel;
import rx.subjects.PublishSubject;

public class Store {

    private List<RepositoryViewModel> list;
    public PublishSubject<StoreChangeEvent> repositoriesStream = PublishSubject.create();



    public void onChange(StoreAction event) {

        switch (event.getActionType()) {
            case REPOSITORY_CHANGE:
                list = ((RepositoryChangeAction) event).getData();
                repositoriesStream.onNext(new StoreChangeEvent());
                Log.d("STORE", "change list");
        }
    }

    public List<RepositoryViewModel> getList() {
        return list;
    }
}
