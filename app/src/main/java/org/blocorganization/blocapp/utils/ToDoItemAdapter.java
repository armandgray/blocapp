package org.blocorganization.blocapp.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.ToDoItem;

import java.util.List;

import static org.blocorganization.blocapp.utils.DateTimeFormatHandler.setSlashDateWith;
import static org.blocorganization.blocapp.utils.FieldUtilities.setTextForTextViewWith;

public class ToDoItemAdapter extends RecyclerView.Adapter<ToDoItemAdapter.ViewHolder> {

    private static final String NEW = "New";
    private static final String PLANNING = "Planning";
    private static final String ACTIVE = "Active";
    private static final String DELEGATED = "Delegated";
    private static final String EXECUTE = "Execute";
    private static final String COMPLETE = "Complete";
    private List<ToDoItem> toDoItems;

    public ToDoItemAdapter(List<ToDoItem> toDoItems) {
        this.toDoItems = toDoItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_listitem, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        ToDoItem toDoItem = toDoItems.get(position);

        TextView tvTimeLeft = viewHolder.tvTimeLeft;
        TextView tvTimeLeftUnits = viewHolder.tvTimeLeftUnits;
        TextView tvTitle = viewHolder.tvTitle;
        TextView tvTimestamp = viewHolder.tvTimestamp;
        TextView tvSourceRecord = viewHolder.tvSourceRecord;
        ImageView ivStatus = viewHolder.ivStatus;
        TextView tvStatus = viewHolder.tvStatus;

        setTextForTextViewWith(toDoItem.getTitle(), tvTitle);
        setSlashDateWith(toDoItem.getTimestamp(), tvTimestamp);
        setTextForTextViewWith(toDoItem.getSourceRecord(), tvSourceRecord);
        setTextForTextViewWith(toDoItem.getStatus(), tvStatus);
        setIvStatusFrom(toDoItem.getStatus(), ivStatus);
    }

    private void setIvStatusFrom(String status, ImageView ivStatus) {
        switch (status) {
            case NEW:
                setIvToEmptyStar(ivStatus);
                return;
            case PLANNING:
                setIvToEmptyStar(ivStatus);
                return;
            case ACTIVE:
                setIvToHalfStar(ivStatus);
                return;
            case DELEGATED:
                setIvToHalfStar(ivStatus);
                return;
            case EXECUTE:
                setIvToFullStar(ivStatus);
                return;
            case COMPLETE:
                setIvToFullStar(ivStatus);
        }
    }

    private void setIvToEmptyStar(ImageView ivStatus) {
        ivStatus.setImageResource(R.drawable.ic_star_outline_white_48dp);
    }

    private void setIvToHalfStar(ImageView ivStatus) {
        ivStatus.setImageResource(R.drawable.ic_star_half_white_48dp);
    }

    private void setIvToFullStar(ImageView ivStatus) {
        ivStatus.setImageResource(R.drawable.ic_star_white_48dp);
    }

    @Override
    public int getItemCount() {
        return toDoItems.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTimeLeft;
        TextView tvTimeLeftUnits;
        TextView tvTitle;
        TextView tvTimestamp;
        TextView tvSourceRecord;
        ImageView ivStatus;
        TextView tvStatus;

        ViewHolder(View itemView) {
            super(itemView);
            tvTimeLeft = (TextView) itemView.findViewById(R.id.tvTimeLeft);
            tvTimeLeftUnits = (TextView) itemView.findViewById(R.id.tvTimeLeftUnits);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvTimestamp = (TextView) itemView.findViewById(R.id.tvTimestamp);
            tvSourceRecord = (TextView) itemView.findViewById(R.id.tvSourceRecord);
            ivStatus = (ImageView) itemView.findViewById(R.id.ivStatus);
            tvStatus = (TextView) itemView.findViewById(R.id.tvStatus);

        }
    }

}
