package com.example.project309.app;

import java.util.ArrayList;

public interface StringRequestInter {

    void setListener(StringAbstractListener stringAbstractListener);

    void makeStringReqInner(String url, ArrayList<JSONVariable> paramList, RequestMethod rm);

}
