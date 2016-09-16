package pl.training.githubbrowser.model.github;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Repository implements Parcelable{

    private long id;
    private String name;
    private String description;
    private int forks;
    private int watchers;
    @SerializedName("stargazers_count")
    private int stars;
    private String language;
    private String homepage;
    private User owner;
    private boolean fork;

    public Repository() {
    }

    protected Repository(Parcel in) {
        id = in.readLong();
        name = in.readString();
        description = in.readString();
        forks = in.readInt();
        watchers = in.readInt();
        stars = in.readInt();
        language = in.readString();
        homepage = in.readString();
        owner = in.readParcelable(User.class.getClassLoader());
        fork = in.readByte() != 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }

    public int getWatchers() {
        return watchers;
    }

    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(forks);
        dest.writeInt(watchers);
        dest.writeInt(stars);
        dest.writeString(language);
        dest.writeString(homepage);
        dest.writeParcelable(owner, flags);
        dest.writeByte((byte) (fork ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel in) {
            return new Repository(in);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }

    };

}
