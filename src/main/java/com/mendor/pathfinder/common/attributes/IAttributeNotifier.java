package com.mendor.pathfinder.common.attributes;

public interface IAttributeNotifier {

    void addListener(IAttributeListener listener);
    void notifyListeners();
}
