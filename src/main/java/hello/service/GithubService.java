package hello.service;

import java.io.IOException;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.stereotype.Service;

import hello.APIConfiguration;
import hello.RepositoryInterface;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class GithubService implements APIConfiguration {

//    private String accessToken = "hQ_IH5YjOHweKpx0ti6_zzXD4qdJa4LXgSbO0TxmPiWcgd6qFxE";
//
//    private RepositoryInterface service;
//
//    public GithubService() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(API_BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        service = retrofit.create(RepositoryInterface.class);
//        this.accessToken = "token " + System.getenv("ACCESS_TOKEN");
//    }
//
//    public List<Repository> getRepositories() throws IOException {
//        Call<List<Repository>> retrofitCall = service.listRepos(accessToken);
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

   
}