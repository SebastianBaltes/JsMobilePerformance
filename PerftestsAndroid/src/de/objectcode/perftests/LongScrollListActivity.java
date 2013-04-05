package de.objectcode.perftests;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import de.objectcode.perftests.utils.RandomValues;

public class LongScrollListActivity extends ListActivity {

    class Item {
        int imgRes;
        String name;
        String description;
    }

    List<Item> items = new ArrayList<Item>();

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        final RandomValues rnd = new RandomValues();
        for (int i = 0; i < 100; i++) {
            Item item = new Item();
            item.imgRes = R.drawable.img0+i;
            item.name = rnd.nextTextWithWords(3);
            item.description = rnd.nextTextWithWords(20);
            items.add(item);
        }
        ArrayAdapter<Item> adapter = new ArrayAdapter<Item>(this, R.layout.dummy_list_item, items) {
            public View getView(int position, View view, ViewGroup parent) {
                Item item = items.get(position);
                if (view == null) {
                    LayoutInflater inflater = (LayoutInflater) LongScrollListActivity.this
                            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.dummy_list_item, parent, false);
                }
                ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
                imageView.setImageResource(item.imgRes);
                TextView textView1 = (TextView) view.findViewById(R.id.textView1);
                textView1.setText(item.name);
                TextView textView2 = (TextView) view.findViewById(R.id.textView2);
                textView2.setText(item.description);

                return view;
            }
        };
        setListAdapter(adapter);
    }

}
