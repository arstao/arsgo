package ars.arsgo.list;


import javax.inject.Singleton;

import ars.arsgo.SampleModule;
import dagger.Component;

/**
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2016/8/16 20:15
 * 修改人：Administrator
 * 修改时间：2016/8/16 20:15
 * 修改备注：
 */
@Singleton
@Component(modules = SampleModule.class)
public interface  ReposComponent  {
    public ReposPresenter presenter();
    public ReposAdapter adapter();
    public void inject(ReposFragment repoFragment);
}
