package eu.android.g14.myfirstapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class MyFirst extends AppCompatActivity {

    ArrayAdapter<String> appListAdapter;
    ArrayList<String> appNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_first);

        appNamesList = new ArrayList<>();
        appNamesList.add("TEST");
        appListAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appNamesList);

        ListView appListView = (ListView) findViewById(R.id.appsListView);
        appListView.setAdapter(appListAdapter);

        Button myFirstButton = (Button) findViewById(R.id.myFirstButton);
        myFirstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent pdfReadersIntent = new Intent();
                pdfReadersIntent.setAction(Intent.ACTION_VIEW);
                pdfReadersIntent.setType("application/pdf");

                PackageManager pm = getPackageManager();
                List<ResolveInfo> info = pm.queryIntentActivities( pdfReadersIntent, PackageManager.MATCH_DEFAULT_ONLY);

                for( ResolveInfo app : info)
                {
                    appNamesList.add(app.activityInfo.applicationInfo.loadLabel(pm).toString());
                }
                appListAdapter.notifyDataSetChanged();
            }
        });
    }
}
