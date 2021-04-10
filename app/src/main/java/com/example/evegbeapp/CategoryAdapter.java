package com.example.evegbeapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        }else if (position == 1) {
            return new ColoursFragment();
        } else if (position == 2) {
            return new DaysFragment();
        } else if (position == 3) {
            return new FamilyFragment();
        } else {
            return new PhrasesFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_numbers);
        } else if (position == 1) {
            return mContext.getString(R.string.category_colours);
        } else if (position == 2) {
            return mContext.getString(R.string.category_days);
        } else if (position == 3) {
            return mContext.getString(R.string.category_family);
        } else {
            return mContext.getString(R.string.category_phrases);
        }

    }
}