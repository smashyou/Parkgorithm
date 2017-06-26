package com.parkgo.parkgorithm.background.instruction.server;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.geo.GeoPoint;
import com.backendless.persistence.BackendlessDataQuery;
import com.parkgo.parkgorithm.background.base.Point;
import com.parkgo.parkgorithm.background.bean.AccountBean;
import com.parkgo.parkgorithm.background.bean.AddressBean;
import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.background.instruction.client.ToastInstruction;
import com.parkgo.parkgorithm.runnable.RunnableAction;
import com.parkgo.parkgorithm.user.Buyer;

/**
 * Created by Antony Lulciuc on 8/6/2016.
 */
public class RequestParkingInstruction implements Instruction {

    /**
     * Execute instruction on data set
     * @param _data [in, out] what to process
     * @return instance of type boolean
     */
    public boolean execute(Object[] _data) {
        Thread findHost;
        Object[] data = (Object[]) _data[1];
        Buyer buyer = (Buyer) data[1];
        AddressBean addressBean = (AddressBean) data[2];
        Point<Double> cpt = buyer.getInfo().getLocation();
        GeoPoint gpt = addressBean.getGeographicalLocation();
        BackendlessDataQuery bdq = new BackendlessDataQuery();
        String whereClause = 39.92848 + "&" + -75.174187 + "&" + cpt.getY() + "&" + cpt.getX(); //gpt.getLongitude() + "&" + gpt.getLatitude() + "&" + cpt.getX() + "&" + cpt.getY();
        Handler handler;
        AccountBean accountBean = buyer.getInfo().getAccountBean();
        String id = null;
        boolean loop = true;
        whereClause += "&" + accountBean.getObjectId() + "&" + accountBean.getContactId();
        bdq.setWhereClause(whereClause);

        try {
            // Store Parking spot id then submit
            Backendless.Persistence.of(AddressBean.class).find(bdq);
        } catch (Exception always_thrown) {
            id = always_thrown.getMessage();
        }

        whereClause = id + "&requester&" + whereClause;

        try {
            Backendless.Persistence.of(AddressBean.class).findById(whereClause);
        } catch (Exception err) {
            loop = !"<insertion:completed>".equals(err.getMessage());
        }

        while (loop) {
            try {
                Backendless.Persistence.of(AddressBean.class).findById(whereClause);
            } catch (Exception err) {
                loop = !("<insertion:completed>".equals(err.getMessage()));
            }
        }


        handler = new Handler(Looper.getMainLooper());
        handler.post(new RunnableAction(new ToastInstruction(), new Object[]{_data[0], "Requesting location", Toast.LENGTH_LONG}));

        // Find host
        findHost = new Thread(new RunnableAction(new CheckIfHasHost(), _data));
        findHost.start();
        return (true);
    }
}
