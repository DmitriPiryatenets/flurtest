package com.example.qbcontenttest.sdk.definitions;

import com.example.qbcontenttest.sdk.objects.RestResponse;

public interface ActionResultDelegate {
    public void completedWithResult(QBQueries.QBQueryType queryType, RestResponse response); 
}
