package ars.arsgo.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateFragment;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import ars.arsgo.Model.ErrorMessageDeterminer;
import ars.arsgo.R;
import ars.arsgo.SampleModule;
import ars.arsgo.list.listener.RecyclerItemClickListener;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2016/8/16 20:01
 * 修改人：Administrator
 * 修改时间：2016/8/16 20:01
 * 修改备注：
 */
public class ReposFragment extends MvpLceViewStateFragment<SwipeRefreshLayout,List<Repo>,ReposView,ReposPresenter>
     implements ReposView,SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    ReposComponent reposComponent;
    @Inject ErrorMessageDeterminer errorMessageDeterminer;
    @Inject
    Picasso picasso;
    private ReposAdapter adapter;

    @Override
    public LceViewState<List<Repo>, ReposView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        injectDependencies();
        return LayoutInflater.from(getActivity()).inflate(R.layout.fragment_repos,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        adapter = reposComponent.adapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity()) {
            @Override
            protected void onItemClick(View view, int position) {
                Toast.makeText(getActivity(),"hah"+position,Toast.LENGTH_SHORT).show();
            }
        });
        contentView.setOnRefreshListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    protected void injectDependencies() {
        reposComponent = DaggerReposComponent.builder().sampleModule(new SampleModule(getActivity())).build();
        reposComponent.inject(this);

    }
    @Override
    public List<Repo> getData() {
        return adapter.getRepos();
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        return errorMessageDeterminer.getErrorMessage(e,pullToRefresh);
    }

    @Override
    public ReposPresenter createPresenter() {
        return reposComponent.presenter();
    }

    @Override
    public void setData(List<Repo> data) {
        adapter.setRepos(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadData(boolean pullToRefresh) {
       presenter.loadData(pullToRefresh);
    }

    @Override
    public void onRefresh() {
        loadData(true);
    }

    @Override public void showError(Throwable e, boolean pullToRefresh) {
        super.showError(e, pullToRefresh);
        contentView.setRefreshing(false);
        e.printStackTrace();
    }

    @Override public void showContent() {
        super.showContent();
        contentView.setRefreshing(false);
    }

    @Override public void showLoading(boolean pullToRefresh) {
        super.showLoading(pullToRefresh);
        if (pullToRefresh && !contentView.isRefreshing()) {
            // Workaround for measure bug: https://code.google.com/p/android/issues/detail?id=77712
            contentView.post(new Runnable() {
                @Override public void run() {
                    contentView.setRefreshing(true);
                }
            });
        }
    }
}
