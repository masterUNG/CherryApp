package masterung.androidthai.in.th.cherryapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import masterung.androidthai.in.th.cherryapp.MainActivity;
import masterung.androidthai.in.th.cherryapp.R;
import masterung.androidthai.in.th.cherryapp.utility.MyAlert;

/**
 * Created by masterung on 18/2/2018 AD.
 */

public class RegisterFragment extends Fragment{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


//        Create Toolbar
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity)getActivity()).setSupportActionBar(toolbar);

        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Register");

        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });


//        Register Controller
        Button button = getView().findViewById(R.id.buttonRegister);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Get Value From EditText
                EditText nameEditText = getView().findViewById(R.id.editTextName);
                EditText userEditText = getView().findViewById(R.id.editTextUser);
                EditText passwordEditText = getView().findViewById(R.id.editTextPassword);

                String nameString = nameEditText.getText().toString().trim();
                String userString = userEditText.getText().toString().trim();
                String passwordString = passwordEditText.getText().toString().trim();

//                Check Space
                if (nameString.isEmpty() || userString.isEmpty() || passwordString.isEmpty()) {
//                    Have Space
                    MyAlert myAlert = new MyAlert(getActivity());
                    myAlert.dialogNormal("Have Space",
                            "Please Fill All Blank");

                } else {
//                    No Space

                }




            }   // onClick
        });



    }   // Main Method

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }
}   // Main Class
