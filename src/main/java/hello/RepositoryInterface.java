package hello;

import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import hello.model.League;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RepositoryInterface {
	
	String accessToken = "hQ_IH5YjOHweKpx0ti6_zzXD4qdJa4LXgSbO0TxmPiWcgd6qFxE";
	
	 @GET("leagues")
	    Call<List<League>> listRepos(@Query("token") String accessToken);

//	    @DELETE("repos/{owner}/{repo}")
//	    Call<DeletePayload> deleteRepo(@Header("Authorization") String accessToken, @Header("Accept") String apiVersionSpec,
//	                                   @Path("repo") String repo, @Path("owner") String owner);

	    @POST("user/repos")
	    Call<Repository> createRepo(@Body Repository repo, @Header("Authorization") String accessToken,
	                                      @Header("Accept") String apiVersionSpec,
	                                      @Header("Content-Type") String contentType);
}
