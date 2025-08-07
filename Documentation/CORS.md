# CORS

## What is CORS?

CORS (Cross Origin Resource Sharing) is a security feature built into web browsers that controls how web pages can make requests to a different domain (origin) than the one that served the web page.

For example, if we do not explicitly allow requests from port 4200, where our Angular frontend is located, our backend would block requests from our frontend by default because it does not know this domain/origin.

So what we needed to do was to allow port 4200 to access our backend via the WebConfig.java file.