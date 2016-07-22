package login.qiyun.com.mydemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import login.qiyun.com.mydemo.R;

public abstract class BaseActivity extends AppCompatActivity {

    public LinearLayout view;
    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_other;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base);
        view = initView();
        int layoutID = setContentViewLayoutID();
        view.addView(View.inflate(this, layoutID,null));
        setContentView(view);


        iv_back = (ImageView) view.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_other = (TextView) view.findViewById(R.id.tv_other);

        setOtherSettings(tv_other);

        initUI();

        tv_title = (TextView) view.findViewById(R.id.tv_title);
        setTitleText(tv_title);
    }

    protected abstract void initUI();

    public abstract int setContentViewLayoutID();

    private LinearLayout initView() {
        LinearLayout view = (LinearLayout) View.inflate(this, R.layout.activity_base,null);

        return view;
    }

    public abstract void setTitleText(TextView tv_title);

    public abstract void setOtherSettings(TextView tv_other);

    public View getView() {
        return view;
    }
}
