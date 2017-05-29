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
package demo.georgiosafo.com.socialnetworkexample.data.repository.store;

import java.util.HashMap;
import java.util.List;

import demo.georgiosafo.com.socialnetworkexample.data.model.local.UserNewsLocalData;
import demo.georgiosafo.com.socialnetworkexample.data.repository.interfaces.IDataMemoryStore;

/**
 * Created by gevorksafaryan on 23.05.17.
 */

public class UserNewsDataMemoryStore implements IDataMemoryStore<List<UserNewsLocalData>> {


    List<UserNewsLocalData> data;

    @Override
    public List<UserNewsLocalData> getDatawithParams(HashMap<String, Object> map) {
        return data;
    }

    @Override
    public void setData(List<UserNewsLocalData> data) {
        this.data = data;
    }

    @Override
    public boolean isCached() {
        return data != null;
    }
}
