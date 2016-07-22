package login.qiyun.com.mydemo.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import login.qiyun.com.mydemo.R;
import login.qiyun.com.mydemo.activity.BaseActivity;
import login.qiyun.com.mydemo.activity.LoginActivity;
import login.qiyun.com.mydemo.activity.Setting_ChangePwdActivity;
import login.qiyun.com.mydemo.activity.Setting_aboutActivity;
import login.qiyun.com.mydemo.activity.Setting_comActivity;
import login.qiyun.com.mydemo.activity.Setting_nameActivity;

/**
 * 设置
 */
public class SettingFragment extends BaseFragment{

    private LinearLayout ll_name;
    private LinearLayout ll_change_pwd;
    private LinearLayout ll_com;
    private LinearLayout ll_version;
    private LinearLayout ll_about;
    private Button btn_out;
    private TextView tv_name;
    private SharedPreferences sp;
    private String realName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab_setting,container,false);

        sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        realName = sp.getString("RealName", "");

        ll_name = (LinearLayout) view.findViewById(R.id.ll_name);
        ll_change_pwd = (LinearLayout) view.findViewById(R.id.ll_change_pwd);
        ll_com = (LinearLayout) view.findViewById(R.id.ll_com);
        ll_version = (LinearLayout) view.findViewById(R.id.ll_version);
        ll_about = (LinearLayout) view.findViewById(R.id.ll_about);

        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_name.setText(realName);

        btn_out = (Button) view.findViewById(R.id.btn_out);

        ll_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), Setting_nameActivity.class));
            }
        });
        ll_change_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), Setting_ChangePwdActivity.class));

            }
        });
        ll_com.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), Setting_comActivity.class));

            }
        });
        ll_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setMessage("暂无新版本");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

            }
        });
        ll_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), Setting_aboutActivity.class));

            }
        });

        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                sp.edit().clear().commit();
                getActivity().startActivity(new Intent(getContext(),LoginActivity.class));
                getActivity().finish();
            }
        });


        return view;
    }

    @Override
    public int setLayoutID() {
        return 0;
    }


}
