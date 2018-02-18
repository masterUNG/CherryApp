package masterung.androidthai.in.th.cherryapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import masterung.androidthai.in.th.cherryapp.R;
import masterung.androidthai.in.th.cherryapp.utility.GetAllDataFromServer;
import masterung.androidthai.in.th.cherryapp.utility.MyAlert;
import masterung.androidthai.in.th.cherryapp.utility.MyConstance;

/**
 * Created by masterung on 17/2/2018 AD.
 */

public class MainFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Register Controller
        TextView textView = getView().findViewById(R.id.textViewRegister);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Replace Fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.contentMainFragment, new RegisterFragment())
                        .addToBackStack(null)
                        .commit();

            }
        });


//        Login Controller
        Button button = getView().findViewById(R.id.buttonLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText userEditText = getView().findViewById(R.id.editTextUser);
                EditText passwordEditText = getView().findViewById(R.id.editTextPassword);

                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

                if (userString.isEmpty() || passwordString.isEmpty()) {

//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.dialogNormal("Have Space",
                            "Please Fill User and Password");

                } else {

//                    No Space
                    try {

                        String tag = "18FebV1";
                        MyConstance myConstance = new MyConstance();
                        boolean statusBoolean = true;   // true ==> User False
                        String resultJSON, nameString = null, passwordTrueString = null;
                        MyAlert myAlert = new MyAlert(getActivity());

                        GetAllDataFromServer getAllDataFromServer = new GetAllDataFromServer(getActivity());
                        getAllDataFromServer.execute(myConstance.getUrlGetAllData());

                        resultJSON = getAllDataFromServer.get();
                        Log.d(tag, "JSON ==> " + resultJSON);

                        JSONArray jsonArray = new JSONArray(resultJSON);
                        for (int i=0; i<jsonArray.length(); i+=1) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            if (userString.equals(jsonObject.getString("User"))) {

                                statusBoolean = false;
                                nameString = jsonObject.getString("Name");
                                passwordTrueString = jsonObject.getString("Password");

                            }   // if

                        }   // for

                        if (statusBoolean) {
                            myAlert.dialogNormal("User False",
                                    "No " + userString + " in my Database");
                        } else if (passwordString.equals(passwordTrueString)) {

                            Toast.makeText(getActivity(), "Welcome " + nameString,
                                    Toast.LENGTH_SHORT).show();

                            getActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.contentMainFragment, ServiceFragment.serviceInstance(nameString))
                                    .commit();

                        } else {

                            myAlert.dialogNormal("Password False",
                                    "Please Try Again Password False");

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }




                }   // if




            }   // onClick
        });




    }   // Main Method

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

}   // Main Class
