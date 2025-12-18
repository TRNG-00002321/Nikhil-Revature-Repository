from dataclasses import dataclass
from typing import List, Dict
import itertools


@dataclass
class Order:
    id: int
    customer_id: int
    items: List[Dict]
    status: str
    total: float = 0.0
    payment_added: bool = False


class OrderService:
    # Simulated in-memory database (shared across instances)
    _orders: Dict[int, Order] = {}
    _id_counter = itertools.count(1)

    def create_order(self, items: List[Dict], customer_id: int) -> int:
        if not items:
            raise ValueError("Items required")

        order_id = next(self._id_counter)

        total = sum(item.get("qty", 0) * 50 for item in items)  # fake pricing

        order = Order(
            id=order_id,
            customer_id=customer_id,
            items=items,
            status="PENDING",
            total=total
        )

        self._orders[order_id] = order
        return order_id

    def add_payment(self, order_id: int, amount: float) -> None:
        order = self._get_existing_order(order_id)

        if amount <= 0:
            raise ValueError("Invalid payment amount")

        order.payment_added = True

    def fulfill(self, order_id: int) -> None:
        order = self._get_existing_order(order_id)

        if not order.payment_added:
            raise RuntimeError("Payment required before fulfillment")

        order.status = "COMPLETED"

    def cancel_order(self, order_id: int) -> bool:
        order = self._get_existing_order(order_id)

        if order.status != "PENDING":
            return False

        order.status = "CANCELLED"
        return True

    def get_order(self, order_id: int) -> Order:
        return self._get_existing_order(order_id)

    def get_orders_by_status(self, status: str) -> List[Order]:
        return [
            order for order in self._orders.values()
            if order.status == status
        ]

    def _get_existing_order(self, order_id: int) -> Order:
        if order_id not in self._orders:
            raise KeyError(f"Order {order_id} not found")
        return self._orders[order_id]
