package ars.arsgo.list;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ars.arsgo.R;

/**
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2016/8/17 15:00
 * 修改人：Administrator
 * 修改时间：2016/8/17 15:00
 * 修改备注：
 */
public class ReposActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new ReposFragment())
                    .commit();
        }
    }
}
