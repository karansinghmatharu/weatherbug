package app.dev.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import app.dev.app.R;
import app.dev.app.databinding.RecyclerItemBinding;
import app.dev.app.model.Model;
import app.dev.app.view.ImageDetailsActivity;

/**
 * Created by KaranDeepSingh on 10/11/2018.
 */

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {


    private List<Model> list;

    public ModelAdapter(List<Model> list) {
        this.list = list;
    }

    @Override
    public ModelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View statusContainer = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new ModelAdapter.ViewHolder(statusContainer);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Model status = list.get(position);
        holder.bindUser(status);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerItemBinding binding;

        public ViewHolder(final View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ImageDetailsActivity.class);
                    intent.putExtra("image",binding.getModel().getFilename());
                    intent.putExtra("title",binding.getModel().getTitle());
                    intent.putExtra("desc",binding.getModel().getDescription());
                    context.startActivity(intent);
                }
            });
        }

        public void bindUser(Model model) {
            binding.setModel(model);
        }
    }
}