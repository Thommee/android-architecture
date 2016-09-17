package pl.training.githubbrowser.model.di;

import javax.inject.Singleton;

import dagger.Component;
import pl.training.githubbrowser.GitHubBrowserApplication;
import pl.training.githubbrowser.view.RepositoriesActivity;

@Singleton
@Component(modules = {MainModule.class, ViewModelModule.class, StoreModule.class })
public interface GraphComponent {

    void inject(RepositoriesActivity mainActivity);

    abstract class Initializer {

        public static GraphComponent init(GitHubBrowserApplication app) {
            return DaggerGraphComponent.builder()
                    .mainModule(new MainModule(app))
                    .build();
        }

    }

}
