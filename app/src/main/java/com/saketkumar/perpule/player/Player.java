package com.saketkumar.perpule.player;

import com.saketkumar.perpule.data.model.App;

public interface Player {
    void prepare();
    void play(App song);
    void pause();
    void release();
}
