package com.lcarino.bucketlist.ui.list.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lcarino.bucketlist.R;

/**
 * Adapter used to display a list of menus on a drawer.
 * Created by luiscarino on 2/26/17.
 */
public class MyDrawerAdapter extends RecyclerView.Adapter<MyDrawerAdapter.ViewHolder> {
    public interface MenuActions {
        void onItemClicked(int adapterPosition);
    }

    final MenuActions menuActions;
    private final String[] dataSet;

    public MyDrawerAdapter(String[] dataSet, MenuActions menuActions) {
        this.dataSet = dataSet;
        this.menuActions = menuActions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drawer_list_item, parent, false);
        return new ViewHolder(view, menuActions);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(dataSet[position], position);
    }


    @Override
    public int getItemCount() {
        return dataSet.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private Context context;
        private Drawable archiveIcon, trashIcon, listIcon;

        public ViewHolder(View itemView, final MenuActions actions) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.drawerItemTextView);
            context = itemView.getContext();
            archiveIcon = context.getDrawable(R.drawable.ic_archive_black_48px);
            trashIcon = context.getDrawable(R.drawable.ic_delete_black_48px);
            listIcon = context.getDrawable(R.drawable.ic_list);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.setBackgroundColor(v.getResources().getColor(R.color.colorPrimaryLight));
                    actions.onItemClicked(getAdapterPosition());
                }
            });
        }

        public void bind(String item, int position) {
            itemView.setTag(position);
            textView.setText(item);
            Drawable icon = null;
            // TODO: 2/27/17 refactor this, menu options should be provided by a remote configuration
            switch (item) {
                case "Archive":
                    icon = archiveIcon;
                    break;
                case "Trash":
                    icon = trashIcon;
                    break;
                case "List":
                    icon = listIcon;
                    break;
            }

            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(icon, null, null, null);
        }

    }


}
