package ars.arsgo.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import ars.arsgo.R;

/**
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2016/8/17 14:18
 * 修改人：Administrator
 * 修改时间：2016/8/17 14:18
 * 修改备注：
 */
public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.VH> {
    private Context context;
    private List<Repo> datas;
    Picasso picasso;
    @Inject
    public ReposAdapter(Context context,Picasso picasso) {
        this.context = context;
        this.picasso = picasso;
    }
    public static class VH extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_desc;
        ImageView iv_avatar;

        public VH(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.name);
            tv_desc = (TextView) itemView.findViewById(R.id.description);
            iv_avatar = (ImageView) itemView.findViewById(R.id.avatar);
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_repo, parent, false);
        return new VH(view);
    }
    public List<Repo> getRepos() {
        return datas;
    }

    public void setRepos(List<Repo> repos) {
        this.datas = repos;
    }
    @Override
    public void onBindViewHolder(VH holder, int position) {
        Repo repo = datas.get(position);
        holder.tv_desc.setText(repo.getDescription());
        holder.tv_name.setText(repo.getName());
        picasso.load(repo.getOwner().getAvatarUrl())
                .placeholder(R.color.grey)
                .error(R.color.grey)
                .into(holder.iv_avatar);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
