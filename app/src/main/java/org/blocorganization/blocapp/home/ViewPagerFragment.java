package org.blocorganization.blocapp.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.blocorganization.blocapp.R;
import org.blocorganization.blocapp.models.Product;

import java.text.NumberFormat;

public class ViewPagerFragment extends Fragment {

    public static final String VIEWPAGER_FRAG = "VIEWPAGER_FRAG";
    public static final String PRODUCT_KEY = "product_key";

    private ViewPagerFragmentListener mListener;
    private Product mProduct;

//    private final List<Product> products = DataProvider.productList;
//    private final int numPages = products.size();
//    private ViewPager mPager;

    public ViewPagerFragment() {
        // Required empty public constructor
    }

    public static ViewPagerFragment newInstance(Product product) {

        Bundle args = new Bundle();
        args.putParcelable(PRODUCT_KEY, product);

        ViewPagerFragment fragment = new ViewPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(context instanceof ViewPagerFragmentListener)) throw new AssertionError();
        mListener = (ViewPagerFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_view_pager, container, false);

//        mPager = (ViewPager) rootView.findViewById(R.id.home_pager);
//        PagerAdapter pagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
//        mPager.setAdapter(pagerAdapter);

        /**
         *  View Pager code
         */

        Bundle args = getArguments();
        if (args == null) throw new AssertionError();

        mProduct = args.getParcelable(PRODUCT_KEY);
        if (mProduct == null) throw new AssertionError();

        //      display text and image
        TextView nameText = (TextView) rootView.findViewById(R.id.nameText);
        nameText.setText(mProduct.getName());

        TextView descriptionText = (TextView) rootView.findViewById(R.id.descriptionText);
        descriptionText.setText(mProduct.getDescription());

        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        String price = formatter.format(mProduct.getPrice());
        TextView priceText = (TextView) rootView.findViewById(R.id.priceText);
        priceText.setText(price);

        String productId = mProduct.getProductId();
        int imageResource = getActivity().getResources()
                .getIdentifier(productId, "drawable", getActivity().getPackageName());
        ImageView iv = (ImageView) rootView.findViewById(R.id.imageView);
        iv.setImageResource(imageResource);

        return rootView;
    }

//    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
//        public ViewPagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return new ViewPagerFragment();
//        }
//
//        @Override
//        public int getCount() {
//            return numPages;
//        }
//    }

    public interface ViewPagerFragmentListener {
        void onFragmentFinish();
    }

}
