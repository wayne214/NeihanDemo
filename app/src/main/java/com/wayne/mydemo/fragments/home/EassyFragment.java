package com.wayne.mydemo.fragments.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.wayne.mydemo.R;
import com.wayne.mydemo.adapters.EssayAdapter;
import com.wayne.mydemo.model.TextEssay;
import com.wayne.mylibrary.NetworkTask;
import com.wayne.mylibrary.NetworkTaskCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class EassyFragment extends Fragment implements NetworkTaskCallback {
    private EssayAdapter mAdapter;
    private List<TextEssay> mItems;
    public EassyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = new LinkedList<>();
        mAdapter=new EssayAdapter(getContext(),mItems);
        //TODO:加载网络资源
        NetworkTask task=new NetworkTask(
                getContext(),
                this
        );
        task.execute("http://ic.snssdk.com/neihan/stream/mix/v1/?content_type=-102&message_cursor=-1&loc_time=1432654641&latitude=40.0522901291784&longitude=116.23490963616668&city=%E5%8C%97%E4%BA%AC&count=30&screen_width=800&iid=2767929313&device_id=2757969807&ac=wifi&channel=baidu2&aid=7&app_name=joke_essay&version_code=400&device_platform=android&device_type=KFTT&os_api=15&os_version=4.0.3&openudid=b90ca6a3a19a78d6");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eassy, container, false);
        ListView listView= (ListView) view.findViewById(R.id.essay_list);
        listView.setAdapter(mAdapter);

        return view;
    }

    @Override
    public void onDestroy() {
        mAdapter=null;
        mItems.clear();
        mItems=null;
        super.onDestroy();
    }

    @Override
    public void onTaskFinished(byte[] data) {
        if (data != null) {
            try {
                String json=new String(data,"UTF-8");

                JSONObject jsonObject=new JSONObject(json);

                JSONObject dataObject = jsonObject.getJSONObject("data");

                JSONArray array =
                        dataObject.getJSONArray("data");

                int len=array.length();
                mItems.clear();
                for (int i = 0; i <len ; i++) {
                    JSONObject obj=array.getJSONObject(i);

                    try {
                        TextEssay essay=new TextEssay();
                        essay.parseJson(obj);
                        mItems.add(essay);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mAdapter.notifyDataSetChanged();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
        }
    }
}
