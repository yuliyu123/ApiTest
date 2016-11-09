package com.example.faw.test.datas;

//import java.util.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by faw on 16/10/27.
 */

public interface Api {

	//protected static  String zhihuApi = "";
	@GET("social")
	Observable <String> getString(@Query("key") String key, @Query("num") String num, @Query("page") int page);

	@GET("social/")
	Observable <NewsGson> getNewsData(@Query("key")String key,@Query("num") String num,@Query("page") int page);

	@GET("meinv/")
	Observable  <MeiNvGson> getPictureData(@Query("key")String key,@Query("num") String num,@Query("page") int page);

	//http://api.tianapi.com/wxnew?key=key&num=num  page=?
	@GET("wxnew/")
	Observable <weixinGson> getWeixinData(@Query("key")String key,@Query("num") String num,@Query("page") int page);

	@GET("wxnew")
	Observable <ComicGson> getComicData(@Query("key")String key,@Query("num") String num,@Query("page") int page);


}
