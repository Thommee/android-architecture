package pl.training.githubbrowser.model.di;

import dagger.Module;
import dagger.Provides;
import pl.training.githubbrowser.flux.Store;
import pl.training.githubbrowser.model.github.GitHub;
import pl.training.githubbrowser.viewmodel.RepositoriesViewModel;

@Module
public class ViewModelModule {

    @Provides
    RepositoriesViewModel repositoriesViewModel(GitHub gitHub, Store store) {
        return new RepositoriesViewModel(gitHub, store);
    }
}
