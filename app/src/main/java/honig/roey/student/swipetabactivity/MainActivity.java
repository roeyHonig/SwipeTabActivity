package honig.roey.student.swipetabactivity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private int fabClicksCounter = 0;
    private String majorTitleText = "Major Headline of Section: ";
    private String subTitleText = "Sub Headline of Section: ";

    public int getFabClicksCounter() {
        return fabClicksCounter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // reset counter



        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        // This instantiate a new object of class SectionsPagerAdapter. in't an inner class in this activity.
        // inside of that inner class, there will be a call to instantiate a new object of class PlaceholderFragment
        // which is another inner class in this activity.
        // The PlaceholderFragment object is responsiable to the content of the page section
        // how does he knows the page section, he get's it as an argument from the SectionsPagerAdapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fabClicksCounter);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toa();


                fabClicksCounter++;
                mSectionsPagerAdapter.setClicks(fabClicksCounter);

                /*
                mSectionsPagerAdapter.fragments.get(0).textView.setText("page1 Header "+fabClicksCounter);
                mSectionsPagerAdapter.fragments.get(0).textView2.setText("page 1 title "+fabClicksCounter);
                mSectionsPagerAdapter.fragments.get(1).textView.setText("page 2 Header "+fabClicksCounter);
                mSectionsPagerAdapter.fragments.get(1).textView2.setText("page 2 title "+fabClicksCounter);

                */
                for (int i = 0; i < mSectionsPagerAdapter.fragments.size(); i++) {
                    mSectionsPagerAdapter.fragments.get(i).textView.setText("roey: " + (i+1)+ " after " + fabClicksCounter + " Clicks");
                    mSectionsPagerAdapter.fragments.get(i).textView2.setText("roey: " + (i+1)+ " after " + fabClicksCounter + " Clicks");
                    mSectionsPagerAdapter.fragments.get(i).setClicksOnFab(fabClicksCounter);
                }


            }
        });

    }

    private void toa() {
        Toast.makeText(this,""+mSectionsPagerAdapter.fragments.size(), Toast.LENGTH_LONG).show();
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_FAB_CLICKS = "fab_clicks";
        private int roey;
        private TextView textView;
        private TextView textView2;
        private int clicksOnFab = 0;

        public void setClicksOnFab(int clicksOnFab) {
            this.clicksOnFab = clicksOnFab;
        }

        public TextView getTextView() {
            return textView;
        }

        public TextView getTextView2() {
            return textView2;
        }

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber, int clicks) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            args.putInt(ARG_FAB_CLICKS, clicks);
            fragment.setArguments(args);
            fragment.clicksOnFab = clicks;
            return fragment;
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            textView = (TextView) rootView.findViewById(R.id.section_label);
            textView2 = (TextView) rootView.findViewById(R.id.sub_section_label);

            int numOfClicks = getArguments().getInt(ARG_FAB_CLICKS);

            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1:

                    textView.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView2.setText("roey: " + getArguments().getInt(ARG_SECTION_NUMBER)+ " after " + this.clicksOnFab + " Clicks");
                    break;
                case 2:
                    textView2.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("roey: " + getArguments().getInt(ARG_SECTION_NUMBER)+ " after " + this.clicksOnFab + " Clicks");
                    break;
                case 3:
                    textView.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView2.setText("roey: " + getArguments().getInt(ARG_SECTION_NUMBER)+ " after " + this.clicksOnFab + " Clicks");
                    break;
                case 4:
                    textView2.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("roey: " + getArguments().getInt(ARG_SECTION_NUMBER)+ " after " + this.clicksOnFab + " Clicks");
                    break;
                default:
                    textView.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    break;

            }
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private int clicks;
        private FragmentManager fm;
        private ArrayList<PlaceholderFragment> fragments = new ArrayList<PlaceholderFragment>();

        public void setClicks(int clicks) {
            this.clicks = clicks;
        }

        public SectionsPagerAdapter(FragmentManager fm, int clicks) {
            super(fm);
            this.fm = fm;
            this.clicks = clicks;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            PlaceholderFragment temp = PlaceholderFragment.newInstance(position + 1, clicks);
            fragments.add(temp);
            return temp;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return 4;
        }





    }
}
