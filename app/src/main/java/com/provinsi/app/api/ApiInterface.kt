package com.provinsi.app.api

import com.provinsi.app.helper.GlobalVar
import com.provinsi.app.model.request.BannerRequest
import com.provinsi.app.model.request.EditProvinsiRequest
import com.provinsi.app.model.response.BannerResponse
import com.provinsi.app.model.response.BaseResponse
import com.provinsi.app.model.response.ProvinsiResponse
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @POST(GlobalVar.ENDPOINT)
    fun banner(
        @Body request: BannerRequest
    ): Observable<Response<BannerResponse>>

    @GET("propinsi")
    fun getProvinsi(): Observable<Response<ProvinsiResponse>>

    @FormUrlEncoded
    @POST("propinsi")
    fun addProvinsi(
        @Field("propinsi_name") propinsi_name: String
    ): Observable<Response<BaseResponse>>

    @PUT("propinsi/{id}")
    fun editProvinsi(
        @Path("id") id: String,
        @Body request: EditProvinsiRequest
    ): Observable<Response<BaseResponse>>

    @DELETE("propinsi/{id}")
    fun deleteProvinsi(
        @Path("id") id: String
    ): Observable<Response<BaseResponse>>
}