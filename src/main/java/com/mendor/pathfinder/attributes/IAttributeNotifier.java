package com.mendor.pathfinder.attributes;

public interface IAttributeNotifier {

    void addListener(IAttributeListener listener);
    void notifyListeners();
}
