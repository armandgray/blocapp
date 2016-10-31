package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;

import java.util.List;

import static org.blocorganization.blocapp.campaigns.CampaignDetailActivity.getDPI;

public class CampaignThemeAdapter extends
        RecyclerView.Adapter<CampaignThemeAdapter.ViewHolder> {

    private List<Campaign> mCampaigns;
    private Activity mActivity;

    // Pass in the contact array into the constructor
    public CampaignThemeAdapter(Activity activity, List<Campaign> campaigns) {
        mCampaigns = campaigns;
        mActivity = activity;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.campaign_listitem, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(CampaignThemeAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Campaign campaign = mCampaigns.get(position);
        DisplayMetrics metrics = new DisplayMetrics();
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int containerDimens = getDPI(120, metrics);

        LinearLayout campaignItemContainer = viewHolder.campaignItemContainer;
        ImageView ivCampaignImage = viewHolder.ivCampaignImage;
        TextView tvCampaignMonth = viewHolder.tvCampaignMonth;
        TextView tvCampaignDate = viewHolder.tvCampaignDate;
        TextView tvCampaignDetails = viewHolder.tvCampaignDetails;
        TextView tvCampaignTitle = viewHolder.tvCampaignTitle;

//        ivCampaignImage.setImageResource(campaign.getCampaignPhoto());
        tvCampaignDetails.setText(campaign.getDate() + ",\n by " + campaign.getAdmin());

        if (campaign.getTitle() != null) {
            tvCampaignTitle.setText(campaign.getTitle());
        } else {
            campaignItemContainer.removeView(campaignItemContainer.getChildAt(1));
            campaignItemContainer.removeView(campaignItemContainer.getChildAt(2));
            campaignItemContainer.setLayoutParams(new LinearLayout.LayoutParams(containerDimens, containerDimens));
        }
        if (campaign.getMonth() != null) {
            tvCampaignMonth.setText(campaign.getMonth());
            tvCampaignDate.setText(campaign.getDate());
        } else {
            tvCampaignMonth.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mCampaigns.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout campaignItemContainer;
        public ImageView ivCampaignImage;
        public TextView tvCampaignMonth;
        public TextView tvCampaignDate;
        public TextView tvCampaignTitle;
        public TextView tvCampaignDetails;
        public LinearLayout itemDetailsContainer;
        public View border;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            campaignItemContainer = (LinearLayout) itemView.findViewById(R.id.campaignItemContainer);
            ivCampaignImage = (ImageView) itemView.findViewById(R.id.ivCampaignImage);
            tvCampaignMonth = (TextView) itemView.findViewById(R.id.tvCampaignMonth);
            tvCampaignDate = (TextView) itemView.findViewById(R.id.tvCampaignDate);
            tvCampaignTitle = (TextView) itemView.findViewById(R.id.tvCampaignTitle);
            tvCampaignDetails = (TextView) itemView.findViewById(R.id.tvCampaignDetails);
            itemDetailsContainer = (LinearLayout) itemView.findViewById(R.id.item_details_container);
            border = itemView.findViewById(R.id.campaign_item_border);
        }
    }

}
