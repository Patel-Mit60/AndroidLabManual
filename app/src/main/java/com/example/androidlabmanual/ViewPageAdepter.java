package com.example.androidlabmanual;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPageAdepter extends FragmentStateAdapter {

    public ViewPageAdepter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0){
            return new BlankFragment1();
        }
        else {
            return new BlankFragment2();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
