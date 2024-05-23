from flask import Flask
from flask import request, jsonify
from service.messageService import MessageService
from kafka import KafkaProducer
import json

app = Flask(__name__)
app.config.from_pyfile('config.py')

messageService = MessageService()
producer = KafkaProducer(bootstrap_servers=['localhost:9092'],
                         value_serializer=lambda v: json.dumps(v).encode('utf-8'))

@app.route('/v1/ds/message', methods=['POST'])
def handle_message():
    message = request.json.get('message')
    result = messageService.process_message(message)
    if result is not None:
        # Convert the result to a JSON-serializable dictionary
        serialized_result = json.dumps(result)
        
        # Send the serialized result to the Kafka topic
        producer.send('expense_service', serialized_result)
        
        # Return the result as a JSON response
        return jsonify(result)
    # serialized_result = result.json()

    # # Send the serialized result to the Kafka topic
    # producer.send('expense_service', serialized_result)

    # return jsonify(result)
    # return jsonify(result)
     # Ensure the result is JSON serializable
    # if result is not None:
    #     return jsonify(result.dict())
    # else:
    #     return jsonify({"error": "Invalid message"}), 400

@app.route('/', methods=['GET'])
def handle_get():
    return 'Hello world'


if __name__ == "__main__":
    app.run(host="localhost", port= 8000 ,debug=True)