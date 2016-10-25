package org.blocorganization.blocapp.campaigns;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.blocorganization.blocapp.R;

public class CampaignDetailActivity extends AppCompatActivity {

    DisplayMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_detail);

        metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        final LinearLayout interestedBtn = (LinearLayout) findViewById(R.id.btn_container_red);
        final ImageView interestedBtnImage = (ImageView) interestedBtn.getChildAt(0);
        interestedBtnImage.setImageResource(R.drawable.ic_star_white_48dp);
        final TextView interestedBtnText = (TextView) interestedBtn.getChildAt(1);
        interestedBtnText.setText("Interested");

        // Add ImageView OnClick
//        final ImageView menu_icon = new ImageView(this);
//        menu_icon.setPadding(getDPI(5, metrics), 0, 0, 0);
//        menu_icon.setLayoutParams(new LayoutParams(getDPI(30, metrics), getDPI(30, metrics)));
//        menu_icon.setImageResource(R.drawable.ic_menu_down_grey600_48dp);

        interestedBtn.setOnClickListener(new View.OnClickListener() {
            boolean clicked;

            @Override
            public void onClick(View view) {
                interestedBtnText.setText("Going");
                if (clicked) {
                    interestedBtnImage.setImageResource(R.drawable.ic_check_white_48dp);
                    clicked = !clicked;
                } else {
                    interestedBtnImage.setImageResource(R.drawable.ic_plus_white_48dp);
                    clicked = !clicked;
                }
            }
        });

        LinearLayout shareBtn = (LinearLayout) findViewById(R.id.btn_container_gray);
        final ImageView shareBtnImage = (ImageView) shareBtn.getChildAt(0);
        shareBtnImage.setImageResource(R.drawable.ic_share_grey600_48dp);
        final TextView shareBtnText = (TextView) shareBtn.getChildAt(1);
        shareBtnText.setText("Share");

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareBtnImage.setImageResource(R.drawable.ic_check_grey600_48dp);
            }
        });

        final LinearLayout more = (LinearLayout) findViewById(R.id.more_container);
        final LinearLayout details = (LinearLayout) findViewById(R.id.campaign_details_container);
        details.setVisibility(View.GONE);
        more.setOnClickListener(new View.OnClickListener() {
            boolean clicked;
            TextView tv = (TextView) more.getChildAt(0);
            ImageView iv = (ImageView) more.getChildAt(1);

            @Override
            public void onClick(View view) {
                if (clicked) {
                    details.setVisibility(View.GONE);
                    tv.setText("More");
                    iv.animate().rotationX(360).rotationY(0).setDuration(1000);
                    clicked = !clicked;
                } else {
                    details.setVisibility(View.VISIBLE);
                    tv.setText("Less");
                    iv.animate().rotationX(180).rotationY(0).setDuration(1000);
                    clicked = !clicked;
                }
            }
        });
    }

    public static int getDPI(int size, DisplayMetrics metrics){
        return (size * metrics.densityDpi) / DisplayMetrics.DENSITY_DEFAULT;
    }
}
