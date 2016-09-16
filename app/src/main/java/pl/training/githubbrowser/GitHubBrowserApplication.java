package pl.training.githubbrowser;

import android.app.Application;

import pl.training.githubbrowser.model.di.GraphComponent;

public class GitHubBrowserApplication extends Application {

    private static GraphComponent graph;

    @Override
    public void onCreate() {
        super.onCreate();
        graph = GraphComponent.Initializer.init(this);
    }

    public static GraphComponent component() {
        return graph;
    }

}
