package pl.training.githubbrowser.viewmodel;

import java.util.List;

import pl.training.githubbrowser.model.github.GitHub;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class RepositoriesViewModel {

    public final PublishSubject<List<RepositoryViewModel>> repositoriesStream = PublishSubject.create();

    private GitHub gitHub;

    public RepositoriesViewModel(GitHub gitHub) {
        this.gitHub = gitHub;
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
                .subscribe(repositoriesStream::onNext,
                        repositoriesStream::onError
                );
    }
}
