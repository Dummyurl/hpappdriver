package ride.happyy.driver.net.WSAsyncTasks;

import android.os.AsyncTask;

import org.json.JSONObject;

import ride.happyy.driver.model.BasicBean;
import ride.happyy.driver.net.invokers.UpdateDriverLocationInvoker;



public class UpdateDriverLocationTask extends AsyncTask<String, Integer, BasicBean> {

    private UpdateDriverLocationTaskListener updateDriverLocationTaskListener;

    private JSONObject postData;

    public UpdateDriverLocationTask(JSONObject postData) {
        super();
        this.postData = postData;
    }

    @Override
    protected BasicBean doInBackground(String... params) {
        System.out.println(">>>>>>>>>doInBackground");
        UpdateDriverLocationInvoker updateDriverLocationInvoker = new UpdateDriverLocationInvoker(null, postData);
        return updateDriverLocationInvoker.invokeUpdateDriverLocationWS();
    }

    @Override
    protected void onPostExecute(BasicBean result) {
        super.onPostExecute(result);
        if (result != null)
            updateDriverLocationTaskListener.dataDownloadedSuccessfully(result);
        else
            updateDriverLocationTaskListener.dataDownloadFailed();
    }

    public static interface UpdateDriverLocationTaskListener {
        void dataDownloadedSuccessfully(BasicBean basicBean);

        void dataDownloadFailed();
    }

    public UpdateDriverLocationTaskListener getUpdateDriverLocationTaskListener() {
        return updateDriverLocationTaskListener;
    }

    public void setUpdateDriverLocationTaskListener(UpdateDriverLocationTaskListener updateDriverLocationTaskListener) {
        this.updateDriverLocationTaskListener = updateDriverLocationTaskListener;
    }
}
