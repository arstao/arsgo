package ars.arsgo.Model;

import java.util.List;

import ars.arsgo.list.Repo;
import retrofit.http.GET;
import retrofit.http.Headers;
import rx.Observable;

/**
 * @author Hannes Dorfmann
 */
public interface GithubApi {

  @GET("/repositories")
  @Headers("Cache-Control: no-cache")
  public Observable<List<Repo>> getRepos();
}
