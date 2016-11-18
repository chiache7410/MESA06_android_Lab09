package tw.org.iii.lab09;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private LinkedList<HashMap<String,Object>> data;
    private String[] from = {"title", "content", "img"};
    private int[] to = {R.id.item_tv, R.id.item_content, R.id.item_img};
    private int[] img = {R.drawable.b0, R.drawable.b1, R.drawable.b2};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.listview);
        initListView();
    }
    private void initListView() {
        data = new LinkedList<>();
        for (int i=0; i<20; i++) {
            HashMap<String, Object> row = new HashMap<>();
            row.put(from[0], "PPAP" + i);
            row.put(from[1], "apple" + i);
            row.put(from[2], img[(int)(Math.random()*3)]);
            data.add(row);
        }
        adapter = new SimpleAdapter(this, data, R.layout.layout_item, from, to);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("brad", "Click: " + position);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("brad", "Long: " + position);
                delItem(position);
                return true;
            }
        });
    }
    private void delItem(int pos){
        data.remove(pos);
        adapter.notifyDataSetChanged();
    }
}