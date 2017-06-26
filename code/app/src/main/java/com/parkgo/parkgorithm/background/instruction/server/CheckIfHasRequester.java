package com.parkgo.parkgorithm.background.instruction.server;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.backendless.Backendless;
import com.parkgo.parkgorithm.background.bean.AddressBean;
import com.parkgo.parkgorithm.background.instruction.Instruction;
import com.parkgo.parkgorithm.background.instruction.client.ShowTransactionModal;
import com.parkgo.parkgorithm.background.instruction.client.ToastInstruction;
import com.parkgo.parkgorithm.modal.TransactionModal;
import com.parkgo.parkgorithm.runnable.RunnableAction;
import com.parkgo.parkgorithm.user.Seller;
import com.parkgo.parkgorithm.user.properties.Info;
import com.parkgo.parkgorithm.background.bean.AccountBean;
import com.parkgo.parkgorithm.background.bean.CarBean;
import com.parkgo.parkgorithm.background.bean.ContactBean;
import com.parkgo.parkgorithm.background.bean.TransactionBean;

/**
 * Created by Antony Lulciuc on 8/6/2016.
 */
public class CheckIfHasRequester implements Instruction {
    /**
     * Execute instruction on data set
     * @param _data [in, out] what to process
     * @return instance of type boolean
     */
    public boolean execute(Object[] _data){
        AppCompatActivity context         = (AppCompatActivity)_data[0];
        Object[] data                     = (Object[])_data[1];
        Seller seller                     = (Seller)data[1];
        AddressBean addressBean           = (AddressBean)data[3];
        TransactionBean transactionBean   = new TransactionBean();
        TransactionBean result            = null;
        Info info                         = seller.getInfo();
        AccountBean accountBean           = info.getAccountBean();
        TransactionModal transactionModal = new TransactionModal(context);
        Handler handler = new Handler(Looper.getMainLooper());
        ContactBean contactBean;
        CarBean carBean;
        String buyerContactId;
        String[] parse;
        boolean bWasActive = false;


        // Create receipt
        transactionBean.setAccountId(accountBean.getObjectId());
        transactionBean.setOwnContactId(accountBean.getContactId());
        transactionBean.setAddressId(addressBean.getObjectId());
        transactionBean.setAccountState("host");

        try{
            transactionBean = Backendless.Persistence.of(TransactionBean.class).save(transactionBean);
        }catch (Exception err){
            // TODO : handle error for receipt creation
        }

        while (true){
            try {

                if (transactionModal.isActive() == false) {
                    if (bWasActive) {
                        if (transactionModal.getResult() == 0) {
                            transactionBean.setUserResponse(false);
                            transactionBean = Backendless.Persistence.of(TransactionBean.class).save(transactionBean);
                        } else {
                            transactionBean.setUserResponse(true);
                            result = Backendless.Persistence.of(TransactionBean.class).save(transactionBean);

                            if (waitForBuyerResponse(result)) {
                                handler.post(new RunnableAction(new ToastInstruction(), new Object[]{context, "Requester Accepted", Toast.LENGTH_LONG}));
                                return (true);
                            } else {
                                handler.post(new RunnableAction(new ToastInstruction(), new Object[]{context, "Requester declined", Toast.LENGTH_LONG}));
                            }
                        }

                        bWasActive = false;
                    }


                    result = Backendless.Persistence.of(TransactionBean.class).findById(transactionBean.getObjectId());

                    parse = result.getAccountId().split("&");

                    result = Backendless.Persistence.of(TransactionBean.class).findById(transactionBean.getObjectId());

                    if ((buyerContactId = result.getForeignContactId()) == null) {
                        while (parse[1].equals(result.getAccountId().split("&")[1])) {
                            result = Backendless.Persistence.of(TransactionBean.class).findById(transactionBean.getObjectId());
                        }
                    }

                    if (buyerContactId != null) {
                        contactBean = Backendless.Persistence.of(ContactBean.class).findById(buyerContactId);
                        transactionModal.setForeignName(contactBean.getUserName());

                        if (contactBean.getCarId() != null) {
                            carBean = Backendless.Persistence.of(CarBean.class).findById(contactBean.getCarId());
                            transactionModal.setUserInfo("User Score : " + contactBean.getUserScore() +
                                    "Car Model  : " + carBean.getModel() +
                                    "Car Year   : " + carBean.getYear() +
                                    "Car Color  : " + carBean.getColor());
                        }

                        handler.post(new RunnableAction(new ShowTransactionModal(), new Object[]{context, transactionModal}));
                    } else {
                        Thread.sleep(3000);
                    }

                }else{
                    bWasActive = true;
                    Thread.sleep(3000);
                }
            }catch (Exception err){
                System.out.println(err.getMessage());
                break;
            }
        }

        return (false);
    }


    private boolean waitForBuyerResponse(TransactionBean _tb)throws Exception{
        while (_tb.getForeignResponse() == null) {
            _tb = Backendless.Persistence.of(TransactionBean.class).findById(_tb.getObjectId());
            Thread.sleep(1000);
        }
        return (_tb.getForeignResponse());
    }
}
