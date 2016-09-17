package pl.training.githubbrowser.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.training.githubbrowser.GitHubBrowserApplication;
import pl.training.githubbrowser.R;
import pl.training.githubbrowser.viewmodel.RepositoriesViewModel;
import pl.training.githubbrowser.viewmodel.RepositoryViewModel;
import retrofit2.adapter.rxjava.HttpException;
import rx.Subscription;

public class RepositoriesActivity extends AppCompatActivity {

    @Inject
    RepositoriesViewModel viewModel;
    Subscription subscription;

    @BindView(R.id.repositories_recycler_view)
    RecyclerView repositoriesRecycleView;
    @BindView(R.id.edit_text_username)
    EditText usernameEditText;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.text_info)
    TextView infoTextView;
    @BindView(R.id.button_search)
    ImageButton searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
        GitHubBrowserApplication.component().inject(this);
        ButterKnife.bind(this);
        setupRepositoriesRecycleView();
        setupUsernameEditText();
        setupSearchButton();
        subscription = viewModel.repositoriesStream.subscribe(
                repositoryViewModels -> {
                    showRepositories(repositoryViewModels);
                    onLoadingCompleted();
                },
                this::showError);
    }

    private void setupRepositoriesRecycleView() {
        RepositoryAdapter adapter = new RepositoryAdapter();
        repositoriesRecycleView.setAdapter(adapter);
        repositoriesRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupUsernameEditText() {
        usernameEditText.addTextChangedListener(UsernameTextWatcher);
        usernameEditText.setOnEditorActionListener((view, actionId, event) -> {
            if (EditorInfo.IME_ACTION_SEARCH != actionId) {
                return false;
            }
            loadGitHubRepositories(usernameEditText.getText().toString());
            return true;
        });
    }

    private void setupSearchButton() {
        searchButton.setOnClickListener(__ -> loadGitHubRepositories(usernameEditText.getText().toString()));
    }

    private void loadGitHubRepositories(String username) {
        progressBar.setVisibility(View.VISIBLE);
        repositoriesRecycleView.setVisibility(View.GONE);
        infoTextView.setVisibility(View.GONE);
        viewModel.loadRepositories(username);
    }

    private void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(usernameEditText.getWindowToken(), 0);
    }

    private TextWatcher UsernameTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            searchButton.setVisibility(charSequence.length() > 0 ? View.VISIBLE : View.GONE);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

    };

    public void showRepositories(List<RepositoryViewModel> repositories) {
        RepositoryAdapter adapter = (RepositoryAdapter) repositoriesRecycleView.getAdapter();
        adapter.setRepositories(repositories);
        adapter.notifyDataSetChanged();
    }

    public void showError(Throwable throwable) {
        progressBar.setVisibility(View.GONE);
        if (throwable instanceof HttpException && ((HttpException) throwable).code() == 404) {
            infoTextView.setText(R.string.error_username_not_found);
        } else {
            infoTextView.setText(R.string.error_loading_repos);
        }
        infoTextView.setVisibility(View.VISIBLE);
    }

    public void onLoadingCompleted() {
        progressBar.setVisibility(View.GONE);
        if (repositoriesRecycleView.getAdapter().getItemCount() > 0) {
            repositoriesRecycleView.requestFocus();
            repositoriesRecycleView.setVisibility(View.VISIBLE);
            hideSoftKeyboard();
        } else {
            infoTextView.setText(R.string.text_empty_repos);
            infoTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null) {
            subscription.unsubscribe();
        }

    }
}
