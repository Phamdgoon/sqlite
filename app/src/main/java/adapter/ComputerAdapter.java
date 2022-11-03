package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dgoon.mobile.sqlite.R;
import model.Computer;

public class ComputerAdapter extends BaseAdapter {
    private Context context;
    private List<Computer> list;

    public ComputerAdapter(Context context, List<Computer> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int iPosition) {
        return list.get(iPosition);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_computer_item, null);
        }
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvPrice = view.findViewById(R.id.tvPrice);

        Computer cmp = list.get(i);
        tvName.setText(cmp.getName());
        tvPrice.setText("" + cmp.getPrice());
        return view;
    }
}
