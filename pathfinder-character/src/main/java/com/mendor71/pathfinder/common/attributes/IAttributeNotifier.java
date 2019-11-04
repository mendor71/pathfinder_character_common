package com.mendor71.pathfinder.common.attributes;

public interface IAttributeNotifier {

    void addListener(IAttributeListener listener);
    void notifyListeners();
}
