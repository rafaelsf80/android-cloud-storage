package es.rafaelsf80.app.cloudstorage;

import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.cloud.AuthCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main extends AppCompatActivity {

    // Credentials file: this file is stored in the assets/ directory. Replace it with yours.
    private final String CREDENTIALS_FILE = "doneval-cloud-d164a2981f94.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // Background task for network access
        new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void... params) {

                try {
                    AssetManager am = Main.this.getAssets();
                    InputStream isCredentialsFile = am.open(CREDENTIALS_FILE);

                    Storage storage = StorageOptions.builder()
                            .projectId("decent-envoy-503")
                            .authCredentials(AuthCredentials.createForJson(isCredentialsFile))
                            .build().service();

                    BlobId blobId = BlobId.of("bucket_rafa", "test_android");
                    BlobInfo blobInfo = BlobInfo.builder(blobId).contentType("text/plain").build();
                    // Da error aqui de que no existe crc32, probablmente es por la exclusión
                    Blob blob = storage.create(blobInfo, "Hello, Cloud Storage!".getBytes(UTF_8));
                    Log.d("Main", "created blob_name: " + blob.name());
                } catch (Exception e) {
                    Log.d("Main", e.toString());
                }
                return "Done!";
            }

            protected void onPostExecute(String msg) {
                // Post Code
                //Log.d(TAG, msg);
                Log.d("Main", msg);
            }
        }.execute();
    }
}
