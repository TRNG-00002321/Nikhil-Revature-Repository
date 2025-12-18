import allure
import pytest
from order_service import OrderService


@allure.epic("E-Commerce Platform")
@allure.feature("Order Management")
class TestOrderService:

    @allure.story("Create new order")
    @allure.title("Successfully create order with valid items")
    @allure.description("""
    Tests the order creation flow:
    1. Add items to order
    2. Calculate total
    3. Create order record
    4. Return order ID
    """)
    @allure.severity(allure.severity_level.CRITICAL)
    def test_create_order_success(self):
        service = OrderService()
        order_id = service.create_order(
            items=[{"sku": "ITEM1", "qty": 2}],
            customer_id=123
        )
        assert order_id is not None

    @allure.story("Order validation")
    @allure.title("Reject order with empty items")
    @allure.severity(allure.severity_level.NORMAL)
    def test_create_order_empty_items_fails(self):
        service = OrderService()
        with pytest.raises(ValueError, match="Items required"):
            service.create_order(items=[], customer_id=123)

    @allure.story("Complete order flow")
    @allure.title("Process order from creation to fulfillment")
    def test_complete_order_flow(self):
        order_id = self._create_order()
        self._add_payment(order_id)
        self._fulfill_order(order_id)
        self._verify_completion(order_id)

    @allure.step("Create order with items")
    def _create_order(self):
        with allure.step("Initialize order service"):
            service = OrderService()

        with allure.step("Add items to order"):
            items = [{"sku": "SKU1", "qty": 1}]

        with allure.step("Submit order"):
            order_id = service.create_order(items=items, customer_id=1)

        allure.attach(
            str(order_id),
            name="Created Order ID",
            attachment_type=allure.attachment_type.TEXT
        )

        return order_id

    @allure.step("Add payment for order: {order_id}")
    def _add_payment(self, order_id):
        service = OrderService()
        service.add_payment(order_id, amount=100.00)

    @allure.step("Fulfill order: {order_id}")
    def _fulfill_order(self, order_id):
        service = OrderService()
        service.fulfill(order_id)

    @allure.step("Verify order completion")
    def _verify_completion(self, order_id):
        service = OrderService()
        order = service.get_order(order_id)
        assert order.status == "COMPLETED"

    @allure.story("Order cancellation")
    @allure.link("https://jira.company.com/browse/ORD-123", name="User Story")
    @allure.issue("BUG-456", "Fix cancel race condition")
    @allure.testcase("TC-789", "Order Cancel Test Case")
    def test_cancel_order(self):
        # Dynamic title based on test data
        allure.dynamic.title(f"Cancel order in PENDING status")
        allure.dynamic.description("Tests that pending orders can be cancelled")

        service = OrderService()
        order_id = service.create_order(items=[{"sku": "X", "qty": 1}], customer_id=1)

        result = service.cancel_order(order_id)

        assert result is True

    @allure.story("Order queries")
    @pytest.mark.parametrize("status,expected_count", [
        ("PENDING", 5),
        ("COMPLETED", 10),
        ("CANCELLED", 2),
    ])
    def test_get_orders_by_status(self, status, expected_count):
        allure.dynamic.title(f"Get orders with status: {status}")

        service = OrderService()
        orders = service.get_orders_by_status(status)

        # Note: This would fail without proper setup
        # Just demonstrating dynamic titles
        assert isinstance(orders, list)
