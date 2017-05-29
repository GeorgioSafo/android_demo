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
package demo.georgiosafo.com.socialnetworkexample.di.component;

import javax.inject.Singleton;

import dagger.Component;
import demo.georgiosafo.com.socialnetworkexample.SocialNetworkExampleApp;
import demo.georgiosafo.com.socialnetworkexample.data.repository.UserNewsRepository;
import demo.georgiosafo.com.socialnetworkexample.data.repository.UserRepository;
import demo.georgiosafo.com.socialnetworkexample.di.module.CacheModule;
import demo.georgiosafo.com.socialnetworkexample.di.module.LocalModule;
import demo.georgiosafo.com.socialnetworkexample.di.module.NetworkModule;
import demo.georgiosafo.com.socialnetworkexample.di.module.SocialNetworkExampleAppModule;

/**
 * Created by gevorksafaryan on 19.04.17.
 */
@Singleton
@Component(
        modules = {SocialNetworkExampleAppModule.class, NetworkModule.class, LocalModule.class, CacheModule.class}
)
public interface SocialNetworkExampleAppComponent {
    void inject(SocialNetworkExampleApp app);

    UserRepository userRepository();

    UserNewsRepository userNewsRepository();

}
