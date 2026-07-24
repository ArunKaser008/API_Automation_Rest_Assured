                   Cucumber Feature
                          │
                          ▼
                 Step Definition
                          │
                          ▼
                     Business API
                          │
                          ▼
                      ApiClient
             ┌────────────┴────────────┐
             ▼                         ▼
RequestSpecificationFactory   ResponseSpecificationFactory
│                         │
▼                         ▼
Request Logging          Response Validation
│
▼
Response Logging
│
▼
REST Assured


-----------------

                ApiClient
                    │
                    ▼
          HttpExecutorRegistry
                    │
      ┌─────────────┼─────────────┐
      ▼             ▼             ▼
GetExecutor   PostExecutor   PutExecutor
│             │             │
└─────────────┼─────────────┘
▼
BaseExecutor
│
▼
Builds RequestSpecification