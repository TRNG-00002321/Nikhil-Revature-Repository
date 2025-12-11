import pytest
import csv
from pathlib import Path
from src.Calculator import Calculator

@pytest.fixture
def calculator():
    return Calculator()

def test_calculator(calculator):
    result = calculator.add(1, 2)
    assert result == 3

def test_calculator_isEven(calculator):
    result = calculator.is_even(6)
    assert result == True

def test_divide_by_zero(calculator):
    with pytest.raises(ZeroDivisionError):
        calculator.divide(3, 0)

def test_divide_by_zero_context(calculator):
    with pytest.raises(ZeroDivisionError) as context:
        calculator.divide(3, 0)
        assert "zero" in str(context.value).lower()

@pytest.mark.parametrize(
    "num1, num2, expected_sum",
    [
        (1, 2, 3),
        (0, 0, 0),
        (-1, 5, 2),
        (10, -3, 7),
        (2.5, 3.5, 6.0),
    ]
)
def test_add_function(num1, num2, expected_sum):
    result = num1 + num2
    assert result == expected_sum

def load_csv():
    cases = []
    csv_path = Path(__file__).parent / "data.csv"
    with open(csv_path, newline="") as f:
        reader = csv.DictReader(f)
        for row in reader:
            cases.append((
                float(row["num1"]),
                float(row["num2"]),
                float(row["expected_sum"])
            ))
    return cases

@pytest.mark.parametrize("num1, num2, expected_sum", load_csv())
def test_add_function_withCSV(num1, num2, expected_sum):
    assert num1 + num2 == expected_sum