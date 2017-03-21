package Modules;

import android.widget.ListView;

import java.util.List;

/**
 * Created by NguyenVBA on 3/10/2017.
 */

public interface DirectionFinderListener {
    void onDirectionFinderStart();
    void onDirectionFinderSuccess(List<Route> route);

}
