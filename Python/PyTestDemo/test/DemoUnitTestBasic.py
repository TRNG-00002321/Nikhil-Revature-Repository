import unittest
from src.Calculator import Calculator

class TestCalculator(unittest.TestCase):
    def setUp(self):
        self.Calculator = Calculator()

    def test_add(self):
        n1 = 1
        n2 = 2
        expectedResult = 3
        result = self.Calculator.add(n1, n2)
        self.assertEqual(result, expectedResult)

    def tearDown(self):
        self.Calculator = Calculator()

    def test_divide_by_zero(self):
        with self.assertRaises(ZeroDivisionError):
            self.Calculator.divide(6, 0)