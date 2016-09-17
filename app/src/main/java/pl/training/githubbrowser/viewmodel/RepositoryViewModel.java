package pl.training.githubbrowser.viewmodel;

public class RepositoryViewModel {

    private String title;
    private String description;
    private int forks;
    private int watchers;
    private int stars;


    public RepositoryViewModel(pl.training.githubbrowser.model.github.Repository repository) {
        title = repository.getName();
        description = repository.getDescription();
        forks = repository.getForks();
        watchers = repository.getWatchers();
        stars = repository.getStars();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getForks() {
        return forks;
    }

    public int getWatchers() {
        return watchers;
    }

    public int getStars() {
        return stars;
    }
}
