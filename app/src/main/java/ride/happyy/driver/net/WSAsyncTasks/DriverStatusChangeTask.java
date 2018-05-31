package ride.happyy.driver.net.WSAsyncTasks;

import android.os.AsyncTask;

import org.json.JSONObject;

import ride.happyy.driver.model.BasicBean;
import ride.happyy.driver.net.invokers.DriverStatusChangeInvoker;



public class DriverStatusChangeTask extends AsyncTask<String, Integer, BasicBean> {

    private DriverStatusChangeTaskListener driverStatusChangeTaskListener;

    private JSONObject postData;

    public DriverStatusChangeTask(JSONObject postData) {
        super();
        this.postData = postData;
    }

    @Override
    protected BasicBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        DriverStatusChangeInvoker driverStatusChangeInvoker = new DriverStatusChangeInvoker(null, postData);
        return driverStatusChangeInvoker.invokeDriverStatusChangeWS();
    }

    @Override
    protected void onPostExecute(BasicBean result) {
        super.onPostExecute(result);
        if (result != null)
            driverStatusChangeTaskListener.dataDownloadedSuccessfully(result);
        else
            driverStatusChangeTaskListener.dataDownloadFailed();
    }

    public static interface DriverStatusChangeTaskListener {
        void dataDownloadedSuccessfully(BasicBean basicBean);

        void dataDownloadFailed();
    }

    public DriverStatusChangeTaskListener getDriverStatusChangeTaskListener() {
        return driverStatusChangeTaskListener;
    }

    public void setDriverStatusChangeTaskListener(DriverStatusChangeTaskListener driverStatusChangeTaskListener) {
        this.driverStatusChangeTaskListener = driverStatusChangeTaskListener;
    }
}