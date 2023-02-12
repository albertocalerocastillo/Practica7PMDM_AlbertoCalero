package es.studium.practica7pmdmalbertocalero;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import es.studium.practica7pmdmalbertocalero.databinding.FragmentBienvenidaBinding;

public class BienvenidaFragment extends Fragment {

    private FragmentBienvenidaBinding binding;
    public static final String MyPREFERENCES = "MyPrefs";
    Button btnBorrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBienvenidaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnBorrar = view.findViewById(R.id.btnBorrarCredenciales);
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getContext().deleteSharedPreferences("MyPrefs");
                Toast.makeText(getContext(), "Credenciales borradas", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}