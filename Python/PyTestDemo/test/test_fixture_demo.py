import pytest

@pytest.fixture()
def database_connection():
    print("Establishing a connection....")
    connection = "simulated_db_connection"
    yield connection
    print("Closing database connection...")

def test_database_Connection(database_connection):
    print(f"Using database connection: {database_connection}")
    assert database_connection == "simulated_db_connection"

def test_conftest_user(sample_data):
    assert sample_data["name"] == "Alex"
    assert sample_data["age"] == 22
