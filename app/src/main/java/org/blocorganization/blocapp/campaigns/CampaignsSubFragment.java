package org.blocorganization.blocapp.campaigns;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.CommunityEngagementCampaign;

/**
 * A simple {@link Fragment} subclass.
 */
public class CampaignsSubFragment extends Fragment {


    public CampaignsSubFragment() {
        // Required empty public constructor
    }

    public static CampaignsSubFragment newInstance() {

        Bundle args = new Bundle();

        CampaignsSubFragment fragment = new CampaignsSubFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.campaigns_subfragment_campaigns, container, false);

        final CommunityEngagementCampaign toysForTots = new CommunityEngagementCampaign(
                "Armand Gray", "Toys For Tots",
                "The Toys for Tots program is a winter toy drive hosted by BLOC that gives the students of the Claremont Colleges the opportunity to donate toys and cash to organizations in the community.",
                "The members of BLOC are very aware of the economic distinction between the students of the Claremont Colleges and the surrounding community. While most of the students are fairly well-to-do, families in the surrounding community struggle to makes ends meet. With this in mind, BLOC created its Toys for Tots program. ",
                "With this initiative it was BLOCs aim to brighten the holiday season for families in Pomona, Covina, Upland, and Montclair.",
                R.drawable.bloc_flyer_winter_toy_drive,
                "During the month of November, members of BLOC sit in dining halls around campus and ask for donations. To date, BLOC has collected upwards of $2500 in toys and funds. All donations have gone to the Citrus Valley Health Partners pediatric unit and in the future, BLOC will be expanding its donations to various women and children’s shelters in the area.",
                null,
                "5C Dining Halls", "Oct. - Dec.", null, "R.drawable.toy_drive_donations_sheet");

        LinearLayout campaign_item = (LinearLayout) rootView.findViewById(R.id.campaignItemContainer);
        campaign_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CampaignDetailActivity.class);
                intent.putExtras(toysForTots.toBundle());
                startActivity(intent);
            }
        });

        LinearLayout createBtn = (LinearLayout) rootView.findViewById(R.id.btn_container_red);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CampaignDetailActivity.class);
                startActivity(intent);
            }
        });

        ImageView btnImage = (ImageView) createBtn.getChildAt(0);
        btnImage.setImageResource(R.drawable.ic_package_up_white_48dp);
        TextView btnText = (TextView) createBtn.getChildAt(1);
        btnText.setText("Create New Campaign");



        return rootView;
    }

}
