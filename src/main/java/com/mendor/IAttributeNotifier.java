package com.mendor;

public interface IAttributeNotifier {

    void addListener(IAttributeListener listener);
    void notifyListeners();
}
