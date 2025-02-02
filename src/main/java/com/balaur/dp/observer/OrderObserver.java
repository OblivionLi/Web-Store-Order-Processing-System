package com.balaur.dp.observer;

import com.balaur.model.Order;

public interface OrderObserver {
    void update(Order order);
}
