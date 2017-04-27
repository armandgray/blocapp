package org.blocorganization.blocapp.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Campaign;

import java.util.List;

public class CampaignsItemAdapter extends
        RecyclerView.Adapter<CampaignsItemAdapter.ViewHolder> {

    private final List<Campaign> mCampaigns;
    private final Activity mActivity;
    private final Boolean fullItem;

    public CampaignsItemAdapter(Activity activity, Boolean bool, List<Campaign> campaigns) {
        mCampaigns = campaigns;
        mActivity = activity;
        fullItem = bool;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View contactView = inflater.inflate(R.layout.campaign_listitem, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(CampaignsItemAdapter.ViewHolder viewHolder, int position) {
        Campaign campaign = mCampaigns.get(position);
        int containerDimens = GetDpMeasurement.getDPI(mActivity, 120);

        LinearLayout campaignItemContainer = viewHolder.campaignItemContainer;
        ImageView ivCampaignImage = viewHolder.ivCampaignImage;
        TextView tvCampaignDetails = viewHolder.tvCampaignDetails;
        TextView tvCampaignTitle = viewHolder.tvCampaignTitle;

        Picasso.with(mActivity).load(campaign.getPhotoUrl()).into(ivCampaignImage);
        tvCampaignDetails.setText("By " + campaign.getAdmin());

        if (fullItem) {
            tvCampaignTitle.setText(campaign.getTitle());
        } else {
            campaignItemContainer.removeView(campaignItemContainer.getChildAt(1));
            campaignItemContainer.removeView(campaignItemContainer.getChildAt(2));
            campaignItemContainer.setLayoutParams(new LinearLayout.LayoutParams(containerDimens, containerDimens));
        }
    }

    @Override
    public int getItemCount() {
        return mCampaigns.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

         final LinearLayout campaignItemContainer;
         final ImageView ivCampaignImage;
         final TextView tvCampaignTitle;
         final TextView tvCampaignDetails;

         ViewHolder(View itemView) {
            super(itemView);

            campaignItemContainer = (LinearLayout) itemView.findViewById(R.id.campaignItemContainer);
            ivCampaignImage = (ImageView) itemView.findViewById(R.id.ivCampaignImage);
            tvCampaignTitle = (TextView) itemView.findViewById(R.id.tvCampaignTitle);
            tvCampaignDetails = (TextView) itemView.findViewById(R.id.tvCampaignDetails);
        }
    }

}
