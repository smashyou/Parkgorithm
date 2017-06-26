package com.parkgo.parkgorithm.background.instruction.client;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.parkgo.parkgorithm.navigation.fragment.frag.NavItem;
import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.runnable.RunnableAction;

/**
 * Created by Antony Lulciuc on 8/24/2016.
 */
public class FadeOutNavItem implements Instruction{

    @Override
    public boolean execute(Object[] _data) {
        if (_data[0] instanceof NavItem) {
            NavItem navItem = (NavItem) _data[0];
            Handler handler = new Handler(Looper.getMainLooper());

            navItem.fadeOut();
            handler.postDelayed(new RunnableAction(new Instruction() {
                @Override
                public boolean execute(Object[] _data) {
                    NavItem navItem = (NavItem) _data[0];
                    FragmentActivity activity = (FragmentActivity)_data[1];
                    FragmentManager fragmentManager = activity.getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.remove(navItem);
                    fragmentTransaction.commit();
                    return (true);
                }
            }, _data), (Integer)_data[2]);

            return (true);
        }

        return (false);
    }
}
