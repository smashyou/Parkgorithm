package com.parkgo.parkgorithm.navigation.fragment;

import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.parkgo.parkgorithm.R;
import com.parkgo.parkgorithm.navigation.fragment.frag.NavCenter;
import com.parkgo.parkgorithm.navigation.fragment.frag.NavForeignClient;
import com.parkgo.parkgorithm.navigation.fragment.frag.NavItem;
import com.parkgo.parkgorithm.navigation.fragment.frag.NavUserLocation;
import com.parkgo.parkgorithm.navigation.fragment.frag.NavMoveMarker;
import com.parkgo.parkgorithm.navigation.fragment.frag.NavUpload;
import com.parkgo.parkgorithm.navigation.fragment.frag.NavSearchBar;
import com.parkgo.parkgorithm.navigation.fragment.frag.NavState;
import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.background.instruction.client.FadeInNavItem;
import com.parkgo.parkgorithm.background.instruction.client.FadeOutNavItem;
import com.parkgo.parkgorithm.runnable.RunnableAction;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by John
 */
public class NavItemMediator {
    public static final int NAV_ITEM_MOVEMENT_MARKER = 0x00;
    public static final int NAV_ITEM_FOREIGN_CLIENT  = 0x01;
    public static final int NAV_ITEM_SEARCH_BAR      = 0x02;
    public static final int NAV_ITEM_USER_LOCATION   = 0x03;
    public static final int NAV_ITEM_UPLOAD          = 0x04;
    public static final int NAV_ITEM_STATE           = 0x05;
    public static final int NAV_ITEM_CENTER_ON_USER  = 0x06;
    public static final int NAV_ITEM_ALL             = 0xFF;
    private List<NavItem> m_lNavItems;
    private List<Instruction> m_lShowAnim;
    private List<Instruction> m_lHideAnim;
    private List<Integer> m_lShowAnimationDuration;
    private List<Integer> m_lHideAnimationDuration;
    private List<Boolean> m_lNavActive;
    private int m_Show = 0x80;
    private int m_Hide = 0x80;
    private FragmentActivity m_FragmentActivity;

    /**
     * Constructor
     * @param _FragmentActivity [in] Active Fragment Activity to display fragments
     */
    public NavItemMediator(FragmentActivity _FragmentActivity){
        m_lNavItems  = new LinkedList<>();
        m_lShowAnim  = new LinkedList<>();
        m_lHideAnim  = new LinkedList<>();
        m_lNavActive = new LinkedList<>();
        m_lShowAnimationDuration = new LinkedList<>();
        m_lHideAnimationDuration = new LinkedList<>();

        /**
         * Fragment specifications
         *      1 - Fragment
         *      2 - Show animation
         *      3 - Hide animation
         *      4 - show/hide flag
         *      5 - animation duration for show
         *      6 - animation duration for hide
         */

        // MOVEMENT MARKER
        m_lNavItems.add(new NavMoveMarker());
        m_lShowAnim.add(new FadeInNavItem());
        m_lHideAnim.add(new FadeOutNavItem());
        m_lNavActive.add(false);
        m_lShowAnimationDuration.add(250);
        m_lHideAnimationDuration.add(125);

        // FOREIGN CLIENT
        m_lNavItems.add(new NavForeignClient());
        m_lShowAnim.add(new FadeInNavItem());
        m_lHideAnim.add(new FadeOutNavItem());
        m_lNavActive.add(false);
        m_lShowAnimationDuration.add(250);
        m_lHideAnimationDuration.add(125);

        // SEARCH BAR
        m_lNavItems.add(new NavSearchBar());
        m_lShowAnim.add(new FadeInNavItem());
        m_lHideAnim.add(new FadeOutNavItem());
        m_lNavActive.add(false);
        m_lShowAnimationDuration.add(250);
        m_lHideAnimationDuration.add(125);

        // USER LOCATION
        m_lNavItems.add(new NavUserLocation());
        m_lShowAnim.add(new FadeInNavItem());
        m_lHideAnim.add(new FadeOutNavItem());
        m_lNavActive.add(false);
        m_lShowAnimationDuration.add(250);
        m_lHideAnimationDuration.add(125);

        // UPLOAD LOCATION
        m_lNavItems.add(new NavUpload());
        m_lShowAnim.add(new FadeInNavItem());
        m_lHideAnim.add(new FadeOutNavItem());
        m_lNavActive.add(false);
        m_lShowAnimationDuration.add(250);
        m_lHideAnimationDuration.add(125);

        // USER STATE
        m_lNavItems.add(new NavState());
        m_lShowAnim.add(new FadeInNavItem());
        m_lHideAnim.add(new FadeOutNavItem());
        m_lNavActive.add(false);
        m_lShowAnimationDuration.add(250);
        m_lHideAnimationDuration.add(125);

        // CENTER ON USER BUTTON
        m_lNavItems.add(new NavCenter());
        m_lShowAnim.add(new FadeInNavItem());
        m_lHideAnim.add(new FadeOutNavItem());
        m_lNavActive.add(false);
        m_lShowAnimationDuration.add(250);
        m_lHideAnimationDuration.add(125);

        m_FragmentActivity = _FragmentActivity;
    }

    /**
     * Determines if fragment in question is being shown or not
     * @param _NAV_ITEM [in] fragment in question
     * @return boolean, true if it is shown else false.
     */
    public boolean isShown(int _NAV_ITEM){
        if (_NAV_ITEM > -1 && _NAV_ITEM < m_lNavActive.size())
            return (m_lNavActive.get(_NAV_ITEM));
        else
            return (false);
    }

    /**
     * Shows desired fragment
     * @param _NAV_ITEM [in] desired fragment to show
     */
    public void show(Integer _NAV_ITEM){
        if (_NAV_ITEM < m_lNavActive.size() && !m_lNavActive.get(_NAV_ITEM)) {
            m_lNavActive.set(_NAV_ITEM, true);
            switch (m_Show = _NAV_ITEM) {
                case NAV_ITEM_SEARCH_BAR:
                    showItem(R.id.nav_item_top);
                    break;
                case NAV_ITEM_FOREIGN_CLIENT:
                case NAV_ITEM_USER_LOCATION:
                case NAV_ITEM_UPLOAD:
                case NAV_ITEM_MOVEMENT_MARKER:
                    showItem(R.id.nav_item_middle);
                    break;
                case NAV_ITEM_CENTER_ON_USER:
                    showItem(R.id.nav_item_bottom);
            }
        }
    }

    /**
     * Hide desired fragment
     * @param _NAV_ITEM [in] fragment to hide
     */
    public void hide(Integer _NAV_ITEM){
        if (_NAV_ITEM < m_lNavActive.size() && m_lNavActive.get(_NAV_ITEM)) {
            m_lNavActive.set(_NAV_ITEM, false);
            switch (m_Hide = _NAV_ITEM) {
                case NAV_ITEM_FOREIGN_CLIENT:
                case NAV_ITEM_UPLOAD:
                case NAV_ITEM_MOVEMENT_MARKER:
                case NAV_ITEM_SEARCH_BAR:
                case NAV_ITEM_USER_LOCATION:
                case NAV_ITEM_STATE:
                case NAV_ITEM_CENTER_ON_USER:
                    hideItem();
            }
        }else{
            if (_NAV_ITEM == NAV_ITEM_ALL){
                int iSize = m_lNavItems.size();
                for (int i = 0; i < iSize; i++){
                    if (m_lNavActive.get(i)){
                        m_lNavActive.set(i, false);
                        m_Hide = i;
                        hideItem();
                    }
                }
            }
        }
    }

    /**
     * Displays desired item
     * @param _where [in] desired layout to display fragment
     */
    private void showItem(Integer _where){
        Handler handler = new Handler();
        handler.postDelayed(new RunnableAction(new Instruction() {
            @Override
            public boolean execute(Object[] _data) {
                try {
                    FragmentManager mapFragment = m_FragmentActivity.getSupportFragmentManager();
                    FragmentTransaction ft = mapFragment.beginTransaction();
                    NavItem navItem = m_lNavItems.get((Integer)_data[0]);
                    Handler displayHandler = new Handler();

                    ft.add((int)_data[1], navItem, "nav_items");
                    ft.commit();
                    displayHandler.post(new RunnableAction(m_lShowAnim.get((Integer)_data[0]), new Object[]{navItem}));
                }catch (Exception err){
                    System.out.println(err.getMessage());
                    return (false);
                }

                return true;
            }
        }, new Object[]{m_Show, _where}), m_lShowAnimationDuration.get(m_Show));
    }

    /**
     * Hides desired item
     */
    private void hideItem(){
        Handler handler = new Handler();
        handler.post(new RunnableAction(m_lHideAnim.get(m_Hide), new Object[]{m_lNavItems.get(m_Hide), m_FragmentActivity, m_lShowAnimationDuration.get(m_Hide)}));
    }
}
