package com.example.a1092post;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class API implements MyApiInterface {
    private static final String TAG = "API";
    Context context;

    API(Context context) {
        this.context = context;
    }

    String mUrl = "http://www1.pu.edu.tw/~g1080254/login.php";

    @Override
    public void setLoginApi(String account, String password, String name) {
        Log.d(TAG, "loginInApi: " + "FUCK");
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, mUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d(TAG, "onlogin: " + response);
                    JSONObject object = new JSONObject(response);
                    String success = object.getString("success");
                    if (success.equals("1")) {
                        String name = object.getString("name");
                        String message = object.getString("message");
                        new AlertDialog.Builder(context)
                                .setTitle("您的資料")
                                .setMessage(success + name + message)
                                .setNeutralButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        return;
                                    }
                                })
                                .show();
                    } else {
                        String name = object.getString("name");
                        String message = object.getString("message");
                        new AlertDialog.Builder(context)
                                .setTitle("您的資料")
                                .setMessage(success + name + message)
                                .setNeutralButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        return;
                                    }
                                })
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                    Log.d(TAG, "onResponse: " + "login");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onEMVKD " + error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("account", account);
                map.put("password", password);
                map.put("name", name);
                Log.d(TAG, "getParams: " + "login");
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer " + "");
                Log.d(TAG, "getHeaders: " + "logi123n");
                return map;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                0,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
    }

    public void getAllRestaurant(GetDataInterface getDataInterface) {
        ArrayList<HashMap<String, String>> allRestaurantData = new ArrayList<>();
        String getAllTopic_url = "http://www1.pu.edu.tw/~g1080254/getRestaurant.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, getAllTopic_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);
                try {
                    String str = new String(response.getBytes("ISO-8859-1"), "utf-8");
                    Log.d(TAG, "onResponse: " + str);
                    JSONObject object = new JSONObject(str);
                    String key = object.getString("success");
                    if (key.equals("2")) {
                        JSONArray jsonArray = object.getJSONArray("data");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            HashMap<String, String> map = new HashMap<>();
                            JSONObject data = jsonArray.getJSONObject(i);
                            String name = data.getString("name");
                            map.put("name", name);
                            String address = data.getString("address");
                            map.put("address", address);
                            allRestaurantData.add(map);
                        }
                    } else {
                    }
                    getDataInterface.waitGetAllRestaurantDone(allRestaurantData);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG, "onError" + e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: " + error);
            }
        }) {
        };
        queue.add(stringRequest);
    }
}


