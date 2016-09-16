package pl.training.githubbrowser.model.github;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface GitHub {

    @GET("users/{username}/repos")
    Observable<List<Repository>> getRepositories(@Path("username") String username);

}
