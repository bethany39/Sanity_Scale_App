//package com.example.sanityscaleapp;
//
//import com.google.gson.GsonBuilder;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//public class WeightsController {
//
//    private IWeightsController iWeightsController;
//    public static final String base_url = "https://sanity-scale-api.herokuapp.com/";
//    public final int ID;
//
//    public WeightsController(int ID){
//        this.ID=ID;
//        Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
//                .addConverterFactory(GsonConverterFactory.create(
//                        new GsonBuilder().setPrettyPrinting().create()))
//                .build();
//        iWeightsController = retrofit.create(IWeightsController.class);
//
//    }
//
//    public void getWeights() {
//
//        Call<List<Weight>> call = iWeightsController.getWeights(ID);
//
//        call.enqueue(new Callback<List<Weight>>() {
//            @Override
//            public void onResponse(Call<List<Weight>> call, Response<List<Weight>> response) {
//                if(!response.isSuccessful()){
//                    return;
//                    //should do something for the error handlign
//                }
//
//                return;
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Weight>> call, Throwable t) {
//
//            }
//
//        });
//    }
//
//
//}
