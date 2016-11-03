package ars.arsgo.list;


import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import ars.arsgo.Model.GithubApi;
import ars.arsgo.MvpLceRxPresenter;
import rx.Observable;
import rx.functions.Func1;

/**
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2016/8/16 20:09
 * 修改人：Administrator
 * 修改时间：2016/8/16 20:09
 * 修改备注：
 */
public class ReposPresenter extends MvpLceRxPresenter<ReposView,List<Repo>> {
    GithubApi githubApi;

    @Inject
    ReposPresenter(GithubApi githubApi) {
        this.githubApi = githubApi;
    }

    public void loadData(boolean pullToRefresh) {

        Observable<List<Repo>> listObservable = githubApi.getRepos().flatMap(new Func1<List<Repo>, Observable<List<Repo>>>() {

            @Override
            public Observable<List<Repo>> call(List<Repo> repos) {
                Collections.shuffle(repos);
                return Observable.just(repos);
            }
        });
        subscribe(listObservable, pullToRefresh);
    }
}
