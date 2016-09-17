package pl.training.githubbrowser.viewmodel;

import java.util.List;

import pl.training.githubbrowser.flux.RepositoryChangeAction;
import pl.training.githubbrowser.flux.Store;
import pl.training.githubbrowser.model.github.GitHub;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class RepositoriesViewModel {

    public final PublishSubject<List<RepositoryViewModel>> repositoriesStream = PublishSubject.create();

    private GitHub gitHub;

    private Store store;

    public RepositoriesViewModel(GitHub gitHub, Store store) {
        this.gitHub = gitHub;
        this.store = store;

        store.repositoriesStream
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        storeChangeEvent -> repositoriesStream.onNext(store.getList())
                );

    }



    public void loadRepositories(String username) {
        gitHub.getRepositories(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                // strumien ist zamieniam na strumien elementów
                .flatMapIterable(list -> list)
                // elementy mapuje na rvm
                .map(RepositoryViewModel::new)
                // scalam z powrotem w strumien list
                .toList()
                // zapodaje do publish subjecta
                .subscribe(repositoryViewModels -> {
                        store.onChange(new RepositoryChangeAction(repositoryViewModels));
                },
                        repositoriesStream::onError
                );
    }


}
