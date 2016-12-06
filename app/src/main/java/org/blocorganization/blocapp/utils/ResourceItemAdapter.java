package org.blocorganization.blocapp.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Resource;

import java.util.List;

import static org.blocorganization.blocapp.utils.FieldUtilities.setTextForTextViewWith;

public class ResourceItemAdapter extends
        RecyclerView.Adapter<ResourceItemAdapter.ViewHolder> {

    private List<Resource> resources;

    public ResourceItemAdapter(List<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.resource_listitem, parent, false));
    }

    @Override
    public void onBindViewHolder(ResourceItemAdapter.ViewHolder viewHolder, int position) {
        Resource resource = resources.get(position);

        ImageView ivAdminImage = viewHolder.ivAdminImage;
        TextView tvTitle = viewHolder.tvTitle;
        TextView tvAdminInfo = viewHolder.tvAdminInfo;
        TextView tvTimeSinceCreation = viewHolder.tvTimeSinceCreation;
        TextView tvDesc = viewHolder.tvDesc;
        TextView tvLikes = viewHolder.tvLikes;
        TextView tvComments = viewHolder.tvComments;

        RelativeLayout btnLike = viewHolder.btnLike;
        RelativeLayout btnComment = viewHolder.btnComment;
        RelativeLayout btnShare = viewHolder.btnShare;

        setTextForTextViewWith(resource.getTitle(), tvTitle);
        setTextForTextViewWith(resource.getAdmin(), tvAdminInfo);
        setTextForTextViewWith(resource.getTimestamp(), tvTimeSinceCreation);
        setTextForTextViewWith(resource.getDescription(), tvDesc);



    }

    @Override
    public int getItemCount() {
        return resources.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAdminImage;
        TextView tvTitle;
        TextView tvAdminInfo;
        TextView tvTimeSinceCreation;
        TextView tvDesc;
        TextView tvLikes;
        TextView tvComments;

        RelativeLayout btnLike;
        RelativeLayout btnComment;
        RelativeLayout btnShare;

        ViewHolder(View itemView) {
            super(itemView);
            ivAdminImage = (ImageView) itemView.findViewById(R.id.ivAdminImage);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvAdminInfo = (TextView) itemView.findViewById(R.id.tvAdminInfo);
            tvTimeSinceCreation = (TextView) itemView.findViewById(R.id.tvTimeSinceCreation);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);
            tvComments = (TextView) itemView.findViewById(R.id.tvComments);

            btnLike = (RelativeLayout) itemView.findViewById(R.id.btnLike);
            btnComment = (RelativeLayout) itemView.findViewById(R.id.btnComment);
            btnShare = (RelativeLayout) itemView.findViewById(R.id.btnShare);
        }
    }

}
