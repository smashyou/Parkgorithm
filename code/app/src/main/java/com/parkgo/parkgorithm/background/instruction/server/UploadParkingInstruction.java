package com.parkgo.parkgorithm.background.instruction.server;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.geo.GeoPoint;
import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.background.instruction.client.ToastInstruction;
import com.parkgo.parkgorithm.runnable.RunnableAction;
import com.parkgo.parkgorithm.user.Seller;
import com.parkgo.parkgorithm.user.properties.Info;
import com.parkgo.parkgorithm.background.bean.AccountBean;
import com.parkgo.parkgorithm.background.bean.AddressBean;
import com.parkgo.parkgorithm.background.bean.ParkinghoursBean;
import com.parkgo.parkgorithm.background.bean.ParkingspotBean;

/**
 * Created by Antony Lulciuc on 7/17/2016.
 */
public class UploadParkingInstruction implements Instruction {

    @Override
    public boolean execute(Object[] _data){
        Object[] data = (Object[])_data[1];
        Seller seller = (Seller)data[1];
        Info info     = seller.getInfo();
        AccountBean accountBean           = info.getAccountBean();
        ParkingspotBean parkingspotBean   = (ParkingspotBean)data[2];
        AddressBean addressBean           = (AddressBean)data[3];
        ParkinghoursBean parkinghoursBean = (ParkinghoursBean)data[4];
        Thread findBuyer                  = null;
        Handler handler;
        String secondPush;
        String[] parse;

        // Submit Parking spot data to Backendless server
        parkinghoursBean = Backendless.Persistence.of(ParkinghoursBean.class).save(parkinghoursBean);
        parkingspotBean.setParkingAvailableTimeId(parkinghoursBean.getObjectId());
        parkingspotBean = Backendless.Persistence.of(ParkingspotBean.class).save(parkingspotBean);

        GeoPoint gpt = new GeoPoint();
        gpt.setLatitude(39.92848);
        gpt.setLongitude(-75.174187);
        addressBean.setGeographicalLocation(gpt);

        // Store Parking spot id then submit
        addressBean.setParkingSpotId(parkingspotBean.getObjectId());
        addressBean.setAddress(accountBean.getObjectId() + "&" + accountBean.getContactId() + "&" + addressBean.getAddress());

        try {
            addressBean = Backendless.Persistence.of(AddressBean.class).save(addressBean);
            parse = addressBean.getAddress().split("&");
            secondPush = convertToString(accountBean, addressBean);

            try {
                addressBean = Backendless.Persistence.of(AddressBean.class).findById(secondPush);
            }catch (Exception err){
                // DO NOTHING
            }
            while (true){
                try {
                    addressBean = Backendless.Persistence.of(AddressBean.class).findById(secondPush);
                }catch (Exception err){
                    if ("<insertion:completed>".equals(err.getMessage()))
                        break;
                }
            }

        }catch (Exception err){
            handler = new Handler(Looper.getMainLooper());
            handler.post(new RunnableAction(new ToastInstruction(), new Object[]{_data[0], err.getMessage(), Toast.LENGTH_LONG}));
            return (false);

        }
        data[2] = parkingspotBean;
        data[3] = addressBean;
        data[4] = parkinghoursBean;

        handler = new Handler(Looper.getMainLooper());
        handler.post(new RunnableAction(new ToastInstruction(), new Object[]{_data[0], "Hosting location : " + parse[0], Toast.LENGTH_LONG}));

        // Find buyer
        findBuyer = new Thread(new RunnableAction(new CheckIfHasRequester(), _data));
        findBuyer.start();
        return (true);
    }

    private String convertToString(AccountBean _accountBean, AddressBean _bean){
        StringBuilder bld = new StringBuilder();
        GeoPoint gpt = _bean.getGeographicalLocation();
        String[] parse = _bean.getAddress().split("&");

        bld.append(parse[1]);
        bld.append("&host&");
        bld.append(_bean.getObjectId() + "&");
        bld.append(_bean.getStreetName() + "&");
        bld.append(parse[0] + "&");
        bld.append(_accountBean.getObjectId() + "&");
        bld.append(_accountBean.getContactId() + "&");
        bld.append(gpt.getLatitude() + "&" + gpt.getLongitude() + "&");

        return (bld.substring(0));
    }

}
