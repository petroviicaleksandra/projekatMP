package com.mv.project;

public interface OnSearchAPIListener {

    void onResponse(SearchAPIOdgovor odgovor);
    void onError(String greska);
}
