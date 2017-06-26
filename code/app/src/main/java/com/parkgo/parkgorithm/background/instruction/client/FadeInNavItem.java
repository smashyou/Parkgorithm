package com.parkgo.parkgorithm.background.instruction.client;

import com.parkgo.parkgorithm.navigation.fragment.frag.NavItem;
import com.parkgo.parkgorithm.background.instruction.Instruction;

/**
 * Created by Antony Lulciuc on 8/24/2016.
 */
public class FadeInNavItem implements Instruction{

    @Override
    public boolean execute(Object[] _data) {
        if (_data[0] instanceof NavItem) {
            NavItem navItem = (NavItem) _data[0];
            navItem.setDesign();
            navItem.fadeIn();
            return (true);
        }

        return (false);
    }
}
