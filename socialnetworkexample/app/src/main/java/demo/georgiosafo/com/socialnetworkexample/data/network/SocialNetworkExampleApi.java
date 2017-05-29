/**
 * Copyright (c) 2017 Gevork Safaryan
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package demo.georgiosafo.com.socialnetworkexample.data.network;


import java.util.List;

import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserNewsRemoteData;
import demo.georgiosafo.com.socialnetworkexample.data.model.remote.UserRemoteData;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by gevorksafaryan on 19.04.17.
 */

public interface SocialNetworkExampleApi {

    @GET("a828a5cca4460512767bf80a321d465c/raw/bf8e21fbe871c60288489bb0e62ee89ec7aeb48c/gistfile1.txt")
    Observable<List<UserRemoteData>> getUsers();

    @GET("caa5ed4ddfca464e968772493adaab97/raw/7c69465dd163480dae8e825ba63694a50abc7d3c/gistfile1.txt")
    Observable<List<UserNewsRemoteData>> getNews();
}
