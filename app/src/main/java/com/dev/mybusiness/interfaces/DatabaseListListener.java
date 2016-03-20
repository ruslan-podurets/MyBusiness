package com.dev.mybusiness.interfaces;

import java.util.List;

/**
 * Created by Rusik on 20.03.2016.
 */
public interface DatabaseListListener<K> {

    void onResults(List<K> results);
}
