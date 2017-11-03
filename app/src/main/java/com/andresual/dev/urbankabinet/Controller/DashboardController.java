package com.andresual.dev.urbankabinet.Controller;

import android.util.Log;

import com.andresual.dev.urbankabinet.Activity.RootActivity;
import com.andresual.dev.urbankabinet.Fragment.HomeFragment;
import com.andresual.dev.urbankabinet.Model.BannerModel;
import com.andresual.dev.urbankabinet.Model.ProductModel;
import com.andresual.dev.urbankabinet.Model.ReturnModel;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by andresual on 10/25/2017.
 */

public class DashboardController extends NetworkManager {

    private static DashboardController mInstance;
    public static DashboardController getInstance() {
        if (mInstance == null) {
            mInstance = new DashboardController();
        }
        return mInstance;
    }

    ArrayList<BannerModel> bannerModelArrayList = new ArrayList<>();
    ArrayList<ProductModel> productModelArrayList = new ArrayList<>();
    ArrayList<String> categoryArrayList = new ArrayList<>();
    ArrayList<ProductModel> dataProdukArrayList = new ArrayList<>();
    String urlKitchen;
    String urlWardrobe;
    String urlImgCustom;

    public ArrayList<ProductModel> getDataProdukArrayList() {
        return dataProdukArrayList;
    }

    public void setDataProdukArrayList(ArrayList<ProductModel> dataProdukArrayList) {
        this.dataProdukArrayList = dataProdukArrayList;
    }

    public ArrayList<String> getCategoryArrayList() {
        return categoryArrayList;
    }

    public void setCategoryArrayList(ArrayList<String> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
    }

    public ArrayList<BannerModel> getBannerModelArrayList() {
        return bannerModelArrayList;
    }

    public void setBannerModelArrayList(ArrayList<BannerModel> bannerModelArrayList) {
        this.bannerModelArrayList = bannerModelArrayList;
    }

    public ArrayList<ProductModel> getProductModelArrayList() {
        return productModelArrayList;
    }

    public void setProductModelArrayList(ArrayList<ProductModel> productModelArrayList) {
        this.productModelArrayList = productModelArrayList;
    }

    public String getUrlKitchen() {
        return urlKitchen;
    }

    public void setUrlKitchen(String urlKitchen) {
        this.urlKitchen = urlKitchen;
    }

    public String getUrlWardrobe() {
        return urlWardrobe;
    }

    public void setUrlWardrobe(String urlWardrobe) {
        this.urlWardrobe = urlWardrobe;
    }

    public String getUrlImgCustom() {
        return urlImgCustom;
    }

    public void setUrlImgCustom(String urlImgCustom) {
        this.urlImgCustom = urlImgCustom;
    }

    public void fetchDashboard(final HomeFragment homeFragment) {
        final Map<String, String> params = new HashMap<>();
        params.put("platform", "AND_CUSTOMER");
        params.put("version", "1");
        params.put("token", UserController.getInstance().getCustomerActive().getToken());
        Log.i("PARAMETER SIGN UP",params.toString());

        RequestQueue queue = Volley.newRequestQueue(homeFragment.getActivity());
        StringRequest sr = new StringRequest(Request.Method.POST, urlFetchDashboard+"?token="+UserController.getInstance().getCustomerActive().getToken(), //jika ambil url setelah login harus gini
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("RESPONSE", response);
                        try {
                            JSONObject obj = new JSONObject(response);
                            Log.i("urbankabinet", obj.getString("statuscode"));
                            Log.i("urbankabinet", obj.getString("message"));

                            ReturnModel returnModel = new ReturnModel(obj.getInt("statuscode"), obj.getString("message"), obj);
                            if (returnModel.getStatusCode() == -100 || returnModel.getStatusCode() == -200) {
                                ((RootActivity)homeFragment.getActivity()).maintenanceAndUpdate(returnModel);

                            } else {
                                if (returnModel.getStatusCode() == 1) {
                                    JSONArray dataBanner = obj.getJSONArray("data_banner");
                                    bannerModelArrayList = new ArrayList<>();
                                    for (int i = 0; i < dataBanner.length(); i++) {
                                        JSONObject dataBannerPiece = dataBanner.getJSONObject(i);
                                        BannerModel bannerModel = new BannerModel();
                                        bannerModel.setId(dataBannerPiece.getString("id"));
                                        bannerModel.setImagepath(dataBannerPiece.getString("imagepath"));
                                        bannerModelArrayList.add(bannerModel);
                                    }

                                    JSONArray dataHotItem = obj.getJSONArray("data_hot_item");
                                    productModelArrayList = new ArrayList<>();
                                    for (int i = 0; i < dataHotItem.length(); i++) {
                                        JSONObject dataHotItemPiece = dataHotItem.getJSONObject(i);
                                        ProductModel productModel = new ProductModel();
                                        productModel.setIdProduct(dataHotItemPiece.getString("id"));
                                        productModel.setImageThumbnail(dataHotItemPiece.getString("image_thumbnail"));
                                        productModel.setNameProduct(dataHotItemPiece.getString("name"));
                                        productModel.setDescriptionProduct(dataHotItemPiece.getString("description"));
                                        productModel.setPriceProduct(dataHotItemPiece.getString("price"));
                                        productModel.setKategoriIdProduct(dataHotItemPiece.getString("kategori_id"));
                                        productModel.setJenisProduct(dataHotItemPiece.getString("jenis_produk"));
                                        productModel.setWidthProduct(dataHotItemPiece.getString("width"));
                                        productModel.setHeightProduct(dataHotItemPiece.getString("height"));
                                        productModel.setDepthProduct(dataHotItemPiece.getString("depth"));
                                        productModel.setStokProduct(dataHotItemPiece.getString("stok"));
                                        productModel.setSoldQty(dataHotItemPiece.getString("sold_qty"));
                                        productModelArrayList.add(productModel);
                                    }

                                    categoryArrayList = new ArrayList<>();

                                    urlKitchen = obj.getString("data_img_kitchen");
                                    urlWardrobe = obj.getString("data_img_wardrobe");
                                    urlImgCustom = obj.getString("data_img_custom");

                                    categoryArrayList.add(urlImgCustom);
                                    categoryArrayList.add(urlKitchen);
                                    categoryArrayList.add(urlWardrobe);

                                    JSONArray dataProduk = obj.getJSONArray("data_produk");
                                    for (int i = 0; i < dataProduk.length(); i++) {
                                        dataProdukArrayList = new ArrayList<>();
                                        JSONObject dataProdukPiece = dataProduk.getJSONObject(i);
                                        ProductModel dataProductModel = new ProductModel();
                                        dataProductModel.setIdProduct(dataProdukPiece.getString("id"));
                                        dataProductModel.setImageThumbnail(dataProdukPiece.getString("image_thumbnail"));
                                        dataProductModel.setNameProduct(dataProdukPiece.getString("name"));
                                        dataProductModel.setDescriptionProduct(dataProdukPiece.getString("description"));
                                        dataProductModel.setPriceProduct(dataProdukPiece.getString("price"));
                                        dataProductModel.setKategoriIdProduct(dataProdukPiece.getString("kategori_id"));
                                        dataProductModel.setJenisProduct(dataProdukPiece.getString("jenis_product"));
                                        dataProductModel.setWidthProduct(dataProdukPiece.getString("width"));
                                        dataProductModel.setHeightProduct(dataProdukPiece.getString("height"));
                                        dataProductModel.setDepthProduct(dataProdukPiece.getString("depth"));
                                        dataProductModel.setStokProduct(dataProdukPiece.getString("stok"));
                                        dataProductModel.setSoldQty(dataProdukPiece.getString("sold_qty"));
                                        dataProdukArrayList.add(dataProductModel);
                                    }

                                }
                                homeFragment.handleFetch(returnModel);
                            }

                        } catch (Throwable t) {
                            Log.i("urbankabinet", "Could not parse malformed JSON: \"" + response + "\"");
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                mPostCommentResponse.requestEndedWithError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }
}
