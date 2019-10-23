package hello.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import hello.APIConfiguration;
import hello.RepositoryInterface;
import hello.model.League;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
//@Transactional
public class LeagueService implements APIConfiguration {
//	@Autowired
//	LeagueRepo leagueRepo;

	private String accessToken = "hQ_IH5YjOHweKpx0ti6_zzXD4qdJa4LXgSbO0TxmPiWcgd6qFxE";

	private RepositoryInterface service;
	
	List<League> leagues = null;

	public LeagueService() {

		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.pandascore.co/")
				.addConverterFactory(GsonConverterFactory.create()).build();

		service = retrofit.create(RepositoryInterface.class);
	}

	public List<League> getRepositories() throws IOException {		

		Call<List<League>> call = service.listRepos(accessToken);
		call.enqueue(new Callback<List<League>>() {

			@Override
			public void onResponse(Call<List<League>> call, Response<List<League>> response) {
				// TODO Auto-generated method stub
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@");
				leagues = response.body();
				System.out.println(leagues);
			}

			@Override
			public void onFailure(Call<List<League>> call, Throwable t) {
				// TODO Auto-generated method stub
				System.out.println("=======================");
			}
		});
		return leagues;
		 
	}

//    public Repository createRepository(Repository repo) throws IOException {
//        Call<Repository> retrofitCall = service.createRepo(repo, accessToken, API_VERSION_SPEC, JSON_CONTENT_TYPE);
//
//        Response<Repository> response = retrofitCall.execute();
//
//        if (!response.isSuccessful()) {
//            throw new IOException(response.errorBody() != null
//                    ? response.errorBody().string() : "Unknown error");
//        }
//
//        return response.body();
//    }

//	public LeagueService() {
//		OkHttpClient client = new OkHttpClient();
//
//		client.interceptors().add(new Interceptor() {
//		    @Override
//		    public okhttp3.Response intercept(Chain chain) throws IOException {
//		        Request request = chain.request();
//		        HttpUrl url = request.url().newBuilder().addQueryParameter("token", accessToken).build();
//		        request = request.newBuilder().url(url).build();
//		        return chain.proceed(request);
//		    }
//		});			
//		
//		Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.pandascore.co")
//                .addConverterFactory(GsonConverterFactory.create())
//                .client(client)                
//                .build();
//
//        service = retrofit.create(RepositoryInterface.class);
////        this.accessToken = "token " + System.getenv("ACCESS_TOKEN");
//	}
//	
//	public List<Repository> getRepositories() throws IOException {
//        Call<List<Repository>> retrofitCall = service.listRepos(accessToken, API_VERSION_SPEC);
//
//        Response<List<Repository>> response = retrofitCall.execute();
//
//        if (!response.isSuccessful()) {
//            throw new IOException(response.errorBody() != null
//                    ? response.errorBody().string() : "Unknown error");
//        }
//
//        return response.body();
//    }
//	
//	public Repository createRepository(Repository repo) throws IOException {
//        Call<Repository> retrofitCall = service.createRepo(repo, accessToken, 
//        		API_VERSION_SPEC, JSON_CONTENT_TYPE);
//
//        Response<Repository> response = retrofitCall.execute();
//
//        if (!response.isSuccessful()) {
//            throw new IOException(response.errorBody() != null
//                    ? response.errorBody().string() : "Unknown error");
//        }
//
//        return response.body();
//    }

}
