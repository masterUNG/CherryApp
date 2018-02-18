package masterung.androidthai.in.th.cherryapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import masterung.androidthai.in.th.cherryapp.MainActivity;
import masterung.androidthai.in.th.cherryapp.R;
import masterung.androidthai.in.th.cherryapp.utility.GetAllDataFromServer;
import masterung.androidthai.in.th.cherryapp.utility.MyAlert;
import masterung.androidthai.in.th.cherryapp.utility.MyConstance;

/**
 * Created by masterung on 18/2/2018 AD.
 */

public class ServiceFragment extends Fragment{

    public static ServiceFragment serviceInstance(String nameString) {

        ServiceFragment serviceFragment = new ServiceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("Name", nameString);
        serviceFragment.setArguments(bundle);
        return serviceFragment;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbarService);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getArguments().getString("Name"));


//        Create ListView
        ListView listView = getView().findViewById(R.id.listViewName);
        try {

            MyConstance myConstance = new MyConstance();
            final MyAlert myAlert = new MyAlert(getActivity());
            GetAllDataFromServer getAllDataFromServer = new GetAllDataFromServer(getActivity());
            getAllDataFromServer.execute(myConstance.getUrlGetAllData());

            JSONArray jsonArray = new JSONArray(getAllDataFromServer.get());

            final String[] nameStrings = new String[jsonArray.length()];
            final String[] userStrings = new String[jsonArray.length()];
            final String[] passwordStrings = new String[jsonArray.length()];

            for (int i=0; i<jsonArray.length(); i+=1) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameStrings[i] = jsonObject.getString("Name");
                userStrings[i] = jsonObject.getString("User");
                passwordStrings[i] = jsonObject.getString("Password");

            }   //for

            ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    nameStrings
            );
            listView.setAdapter(stringArrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                    myAlert.dialogNormal(nameStrings[i],
                            "User ==> " + userStrings[i] +
                                    "\n" + "Password ==> " + passwordStrings[i]);

                }
            });



        } catch (Exception e) {
            e.printStackTrace();
        }


    }   // Main Method

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, container, false);
        return view;
    }
}
