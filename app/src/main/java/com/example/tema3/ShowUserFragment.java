package com.example.tema3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShowUserFragment extends Fragment {

    public int id;
    private RecyclerView recyclerView;
    ArrayList<ToDo> todos = new ArrayList<ToDo>();
    ToDoAdaptor adaptor = new ToDoAdaptor();


    public ShowUserFragment(int id ) {
        this.id = id +1;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_user, container, false);

        recyclerView = view.findViewById(R.id.showUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getToDoData(id);

        recyclerView.addOnItemTouchListener(
                new UserClickListener(getContext(), recyclerView, new UserClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        CreteAlarmFragment creteAlarmFragment = new CreteAlarmFragment(((TextView)view.findViewById(R.id.title)).getText().toString().substring(7));
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        if (fragmentManager.findFragmentByTag("ShowUserFragment") != null)
                            fragmentTransaction.replace(R.id.frameLayout, creteAlarmFragment, "CreateAlarmFragment").addToBackStack("second_fragment");
                        fragmentTransaction.commit();
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                    }
                })
        );

        return view;
    }

    private void getToDoData(int id) {
        String url = " https://jsonplaceholder.typicode.com/todos?userId=" + id;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i =0; i < 20; i++){
                        JSONObject jsonObject = response.getJSONObject(i);
                        ToDo toDo = new ToDo();
                        toDo.setUserId(jsonObject.getInt("userId"));
                        toDo.setId(jsonObject.getInt("id"));
                        toDo.setTitle(jsonObject.getString("title"));
                        toDo.setCompleted(jsonObject.getString("completed"));
                        todos.add(toDo);
                    }
                }
                catch (JSONException e){
                    Toast.makeText(getContext(), "Error 1",Toast.LENGTH_SHORT).show();
                }

                adaptor.setData(todos);
                recyclerView.setAdapter(adaptor);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.wtf(error.getMessage(), "utf-8");

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);
    }
}
