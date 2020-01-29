from troposphere import Output, Parameter, Ref, Template
from troposphere.dynamodb import (KeySchema, AttributeDefinition,
                                  ProvisionedThroughput)
from troposphere.dynamodb import Table

t = Template()

t.set_description("AWS CloudFormation template for creating db for products")

myDynamoDB = t.add_resource(Table(
    "products",
    AttributeDefinitions=[
        AttributeDefinition(
            AttributeName="productid",
           AttributeType="S"
        ),
        AttributeDefinition(
                    AttributeName="price",
                   AttributeType="N"
                ),
        AttributeDefinition(
                            AttributeName="currency",
                           AttributeType="S"
                        )
    ],
    KeySchema=[
        KeySchema(
            AttributeName="productid",
            KeyType="HASH"
        )
    ],

    ProvisionedThroughput=ProvisionedThroughput(
        ReadCapacityUnits=5,
        WriteCapacityUnits=5
    )
))

t.add_output(Output(
    "products",
    Value=Ref(myDynamoDB)
))

print(t.to_json())