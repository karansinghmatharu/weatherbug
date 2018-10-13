package app.dev.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import app.dev.app.R;
import app.dev.app.databinding.GridItemBinding;
import app.dev.app.model.Model;
import app.dev.app.view.ImageDetailsActivity;

/**
 * Created by KaranDeepSingh on 10/12/2018.
 */

public class GridAdapter extends BaseAdapter {

    private List<Model> list;

    public GridAdapter(List<Model> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);

        }
        ViewHolder listViewHolder;
        listViewHolder = new ViewHolder(convertView);
        Model status = list.get(position);
        listViewHolder.bindModel(status);
        return convertView;
    }


    static class ViewHolder {
        private GridItemBinding binding;

        public ViewHolder(final View itemView) {
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ImageDetailsActivity.class);
                    intent.putExtra("image", binding.getModel().getFilename());
                    intent.putExtra("title", binding.getModel().getTitle());
                    intent.putExtra("desc", binding.getModel().getDescription());
                    context.startActivity(intent);
                }
            });
        }

        public void bindModel(Model model) {
            binding.setModel(model);
        }
    }
}
