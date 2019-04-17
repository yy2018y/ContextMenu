package com.example.yang.contextmenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity {
    //private TextView txt;
    private String names[]=new String[]{"One","Two","Three","Four","Five"};
    private ActionMode.Callback mCallBack=new ActionMode.Callback() {
        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            // TODO Auto-generated method stub
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            boolean ret = false;
            if (item.getItemId() == R.id.menu_del) {
                mode.finish();
                ret = true;
            }
            return ret;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SimpleAdapter adapter=new SimpleAdapter(this,getData(),R.layout.list_item,new String[]{"title","image"},new int []{R.id.name,R.id.icon});
        ListView listView=(ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);

                //txt=(TextView)findViewById(R.id.menu_txt);
                int ll=adapter.getCount();
                //txt.setText(ll+" selected");
                startActionMode(mCallBack);

            }
        });
    }
    public List<Map<String,Object>>getData(){
        List<Map<String,Object>>list=new ArrayList<Map<String,Object>>();
        Map<String ,Object> map;
        for(int i=0;i<5;i++){
            map=new HashMap<String,Object>();
            map.put("title",names[i]);
            map.put("image",R.drawable.icon);
            list.add(map);
        }
        return list;
    }
}
