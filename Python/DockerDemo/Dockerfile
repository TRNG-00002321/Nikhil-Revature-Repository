# Start creating an image based on the official Python 3.9 slim image
# From is the instruction to specify the base image for subsequent instructions in the Dockerfile
# From is the first instruction in a Dockerfile
FROM python:3.9-slim

# Set the working directory in the container to /app
WORKDIR /app

# Copy the requirements.txt file from the host to the working directory in the container
COPY requirements.txt .

# Install the dependencies specified in requirements.txt
RUN pip install --no-cache-dir -r requirements.txt

# Copy the entire content of the current directory on the host to the working directory in the container
COPY . .

# Expose port 8000 for the application
EXPOSE 8000

# Run the application using python app.py when the container starts
CMD ["python", "app.py"]


