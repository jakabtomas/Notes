package eu.sk.jakab.bsc.network.api;

import java.util.List;

import eu.sk.jakab.bsc.network.model.Note;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by jakab on 1/26/2020.
 */
public interface NotesAPI {
    @GET("notes")
    Observable<List<Note>> geAllNotes();

    @GET("notes/{id}")
    Observable<Note>getNote(@Path("id") int id);

    @POST("notes")
    Observable<Note>createNote(@Body Note note);

    @PUT("notes/{id}")
    Observable<ResponseBody>updateNote(@Path("id") int id, @Body Note note);

    @DELETE("notes/{id}")
    Observable<ResponseBody>deleteNote(@Path("id") int id);

}
