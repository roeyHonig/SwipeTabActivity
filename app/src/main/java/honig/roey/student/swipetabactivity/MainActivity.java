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
    private int fabClicksCounter = 0; // in this example, clicking the FAB changes the data of the pages (sections)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linear_main);
        // Create the adapter that will return a fragment for each of the  4 primary sections - pages -  of the activity.
        // This instantiate a new object of class SectionsPagerAdapter, which is an inner class in this activity.
        // inside of that inner class, there will be a call to instantiate a new object of class PlaceholderFragment
        // which is another inner class in this activity.
        // The PlaceholderFragment object is responsiable to the content of the page section
        // how does he knows the page section, he get's it as an argument from the SectionsPagerAdapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fabClicksCounter);

        mViewPager = (ViewPager) findViewById(R.id.container);
        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabClicksCounter++;
                mSectionsPagerAdapter.setClicks(fabClicksCounter);
                for (int i = 0; i < mSectionsPagerAdapter.fragments.size(); i++) {
                    // directlly change the page we're viewing (and also all the others, but we don't see the others)
                    mSectionsPagerAdapter.fragments.get(i).textView.setText("Page: " + (i+1)+ " after " + fabClicksCounter + " Clicks");
                    mSectionsPagerAdapter.fragments.get(i).textView2.setText("Page: " + (i+1)+ " after " + fabClicksCounter + " Clicks");
                    // change a field in the fragments (pages), so when onCreateView is called (while we scroll them back into view) they will indeed reflect the changes
                    mSectionsPagerAdapter.fragments.get(i).setClicksOnFab(fabClicksCounter);
                }


            }
        });

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
        private TextView textView;
        private TextView textView2;
        private int clicksOnFab = 0;

        public void setClicksOnFab(int clicksOnFab) {
            this.clicksOnFab = clicksOnFab;
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
            fragment.setArguments(args);
            fragment.clicksOnFab = clicks;
            return fragment;
        }

        // since we're using an extenstion of FragmentPagerAdapter, the fragments -representing the pages - are created once and never destroyed.
        // but still when the user scroll them out of the screen, thier views are gone
        // we have to recreate thier respected view every time the user rescroll back to them
        // that is when onCreateView is called
        // so we have to make sure that this methood allaways holds the mose current set of data we want to load
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            textView = (TextView) rootView.findViewById(R.id.section_label);
            textView2 = (TextView) rootView.findViewById(R.id.sub_section_label);


            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1:

                    textView.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView2.setText("Page: " + getArguments().getInt(ARG_SECTION_NUMBER)+ " after " + this.clicksOnFab + " Clicks");
                    break;
                case 2:
                    textView2.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Page: " + getArguments().getInt(ARG_SECTION_NUMBER)+ " after " + this.clicksOnFab + " Clicks");
                    break;
                case 3:
                    textView.setVisibility(View.INVISIBLE);
                    textView2.setVisibility(View.VISIBLE);
                    textView2.setText("Page: " + getArguments().getInt(ARG_SECTION_NUMBER)+ " after " + this.clicksOnFab + " Clicks");
                    break;
                case 4:
                    textView2.setVisibility(View.INVISIBLE);
                    textView.setVisibility(View.VISIBLE);
                    textView.setText("Page: " + getArguments().getInt(ARG_SECTION_NUMBER)+ " after " + this.clicksOnFab + " Clicks");
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
            //getItem() is called by the instantiateItem() internally in order to create a new fragment for a given page.
            // Return a PlaceholderFragment (defined as a static inner class abouve).
            PlaceholderFragment temp = PlaceholderFragment.newInstance(position + 1, clicks);
            // by keeping a ref to the fragment created, we would have an option- whenever we want- to update the fragments (pages) of the adapter
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
