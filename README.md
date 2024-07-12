# MailAPI

The MediaSoft's test task, the requirements for implementation are specified in the repository files, and are also duplicated below in the corresponding paragraph.

The program allows you to register mail items, as well as record movement between intermediate post offices. The possibility of obtaining information about the entire history of movement of a particular shipment by its track number has been implemented.


## Overview
This section demonstrates the requirements for completing the task, the main features of the program by executing queries via Postman, as well as database entities and a small overview of the swagger documentation.


### Requirements
![MediaSoft.Тестовое задание.Java-1](https://github.com/user-attachments/assets/bf89fd58-0306-475f-8ccf-421101ac0306)
![MediaSoft.Тестовое задание.Java-2](https://github.com/user-attachments/assets/ab809baf-f590-4617-aced-3693187f3039)


### Main features
Using Postman, I send a request to add a parcel, specifying its type, delivery address, recipient data, and the office where the parcel was generated.
![Postman - Add a postage](https://github.com/user-attachments/assets/14d65ada-4de3-475a-b6c4-90f10105ee41)
![Postman - The added postage](https://github.com/user-attachments/assets/4140a751-fef0-4da9-a144-9f6cdb57efa4)

Then it is necessary to create a track number for this parcel, by which it will be tracked, it is necessary to specify the name of the final post office. Time of creation, status (IN_TRANSIT) and movement history information will be automatically created and updated.
![Postman - Create a track](https://github.com/user-attachments/assets/76bffff1-ad9f-45e6-8a7a-05a17f9d81ad)
![Postman - The created track - 1](https://github.com/user-attachments/assets/9e4a7d66-1408-45ed-9bb5-15b66e3679e4)
![Postman - The created track - 2](https://github.com/user-attachments/assets/aae06463-1bd0-4a70-b90e-72977764b5ac)

Ideally, when the parcel arrives at the intermediate post office, the worker will send a request to update the status of the parcel, the parcel will have its post office updated, and the track number will receive the new movement information.
![Postman - Reassign the track - 1](https://github.com/user-attachments/assets/b586e0a2-88f9-4551-922a-b9cd8edc46fe)

Once the parcel arrives at its final destination, the worker will send a request from their office, the status of the track number will change to 'ARRIVED'.
![Postman - Reassign the track - 2](https://github.com/user-attachments/assets/c0f7c126-e8e9-4253-a699-86d41dc64670)
![Postman - Reassign the track - 2](https://github.com/user-attachments/assets/996fc3f4-29c8-4f2b-b8a1-213ed77a83e8)

The recipient will see the status update of his parcel, and when he comes to pick it up - the worker will send a new request with the corresponding parameter 'received=true', the status of the parcel's track number will be changed to 'RECEIVED', the parcel will be considered as received.
![Postman - Reassign the track - 3](https://github.com/user-attachments/assets/fab42c91-6aba-4df5-8f97-626cf379f133)
```
{
    "id": 652,
    "createdAt": "2024-07-12 16:57:40.907293",
    "status": "RECEIVED",
    "officeEndpoint": "Plevano",
    "postage": {
        "id": 652,
        "info": "Letter",
        "receiver": {
            "firstName": "Semen",
            "middleName": "Valentyanovich",
            "lastName": "Praskov"
        },
        "receiverAddress": {
            "zipCode": 410023,
            "city": "Chelovekovo",
            "street": "Solnechnaya",
            "houseNumber": "23",
            "apartmentNumber": "21"
        },
        "office": {
            "id": 103,
            "name": "Plevano",
            "officeAddress": {
                "zipCode": 534234,
                "city": "Rerfvc",
                "street": "Sysan",
                "houseNumber": "96",
                "apartmentNumber": "null"
            }
        }
    },
    "shippingInformation": [
        {
            "arrivedAt": "2024-07-12T16:57:40.917319",
            "departuredAt": "2024-07-12T17:02:23.283867",
            "postOfficeName": "Butikovo",
            "postOfficeAddress": {
                "zipCode": 333333,
                "city": "Serebro",
                "street": "Kevrt",
                "houseNumber": "111",
                "apartmentNumber": "null"
            }
        },
        {
            "arrivedAt": "2024-07-12T17:02:23.283610",
            "departuredAt": "2024-07-12T17:03:30.549385",
            "postOfficeName": "Solovo",
            "postOfficeAddress": {
                "zipCode": 112345,
                "city": "Senyt",
                "street": "Kovernoe",
                "houseNumber": "242",
                "apartmentNumber": "null"
            }
        },
        {
            "arrivedAt": "2024-07-12T17:03:30.549360",
            "departuredAt": "2024-07-12T17:07:06.699434",
            "postOfficeName": "Plevano",
            "postOfficeAddress": {
                "zipCode": 534234,
                "city": "Rerfvc",
                "street": "Sysan",
                "houseNumber": "96",
                "apartmentNumber": "null"
            }
        }
    ]
}
```

### Database entities
![MailAPI Database relationships](https://github.com/user-attachments/assets/1f0bef04-bb81-478d-8580-ee9e83c65a6b)

### Swagger documentation

#### Postage API
API Overview
![Postage_MailAPI - Overview](https://github.com/user-attachments/assets/e2fcafeb-cc20-4700-bddd-70fba09e28d0)

Adding a postage
![Postage_MailAPI - Adding](https://github.com/user-attachments/assets/32fd9d74-aa5b-4da9-b164-44f8ce81f54e)

Searching the postage
![Postage_MailAPI - Searching](https://github.com/user-attachments/assets/27966939-10b8-48d2-993d-a52e2840043b)

Deleting the postage
![Postage_MailAPI - Deleting](https://github.com/user-attachments/assets/09facffe-1560-45ab-b022-ba4997aa758e)


#### Package tracking API
API Overview
![PackageTracking_MailAPI - Overview](https://github.com/user-attachments/assets/4f1c1abc-8d88-44f0-9607-e73a5b00794d)

Adding a track
![PackageTracking_MailAPI - Adding](https://github.com/user-attachments/assets/aa228977-6bda-46b5-bcbf-d66c8a8dfdf1)

Searching the track
![PackageTracking_MailAPI - Searching](https://github.com/user-attachments/assets/72f9a120-25eb-4807-9f18-70841abe6df1)

Deleting the track
![PackageTracking_MailAPI - Deleting](https://github.com/user-attachments/assets/5c8c80e1-a163-474b-8dd3-ac94d3044041)


#### Post office API
API Overview
![PostOffice MailApi - Overview](https://github.com/user-attachments/assets/c6340a49-2982-4aea-8b2a-abaabd568ea1)

Adding a post office
![PostOffice_MailAPI - Adding](https://github.com/user-attachments/assets/c4db9e2a-4149-4e1a-a635-b6e0b893c154)

Searching the post office
![PostOffice_MailAPI - Searching](https://github.com/user-attachments/assets/e6b43d8a-14c7-43f5-a6fd-21128e9736eb)

Deleting the post office
![PostOffice_MailAPI - Deleting](https://github.com/user-attachments/assets/8f129adb-7fab-415e-af87-2a945f7cf9f4)
