package com.mv.project;

public interface OnInformacijeApiListener {
    void onResponse(InformacijeAPI info);
    void onError(String poruka);
}
