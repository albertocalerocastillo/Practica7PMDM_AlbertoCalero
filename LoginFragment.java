package es.studium.practica7pmdmalbertocalero;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import es.studium.practica7pmdmalbertocalero.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Usuario = "usuarioKey";
    public static final String Clave = "claveKey";

    EditText editUsuario, editClave;
    Switch guardarCredenciales;
    Button btnAcceder;
    SharedPreferences LoginCredentials;

    @Override
    public View onCreateView(LayoutInflater Layout, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(Layout, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editUsuario = view.findViewById(R.id.editUsuario);
        editClave = view.findViewById(R.id.editClave);
        guardarCredenciales = view.findViewById(R.id.guardarCredenciales);
        btnAcceder = view.findViewById(R.id.btnAcceder);
        LoginCredentials = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String isShared = LoginCredentials.getString(Usuario, null);
        System.out.println(isShared);
        if (isShared != null) {
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_LoginFragment_to_BienvenidaFragment);
        }
        binding.btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editUsuario.getText().toString().equals(getString(R.string.usuario)) && editClave.getText().toString().equals(getString(R.string.clave))) {
                    NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_LoginFragment_to_BienvenidaFragment);
                    if (guardarCredenciales.isChecked()) {
                        String u = editUsuario.getText().toString();
                        String c = editClave.getText().toString();
                        SharedPreferences.Editor editor = LoginCredentials.edit();
                        editor.putString(Usuario, u);
                        editor.putString(Clave, c);
                        editor.commit();
                    }
                } else {
                    Toast.makeText(getContext(), "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}