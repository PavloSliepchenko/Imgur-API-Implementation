# 🖼️ Imgur Saver API 🖼️

## Description
This API provides a simple and efficient way to manage images on Imgur. With this API, you can easily upload images to Imgur, retrieve image details, and delete images from your Imgur account. It is designed to be easy to integrate into your existing applications.

## Features
- Upload Images: Save images to your Imgur account.
- Retrieve Image Details: Fetch details of uploaded images.
- Delete Images: Remove images from your Imgur account.

## How to use
1. You need to register in Imgur. 
2. Register An Application. You can do it by following the link: https://api.imgur.com/oauth2/addclient. After the registration is completed you will get the client ID which should be inserted into imgur.client.id parameter in application.properties
3. API is ready to use! The API implements a simple UI which can be reached by sending a get request to http://localhost:8080/images (if started on the local machine). 

## Important notice
This API doesn't use any database, so once an image is uploaded, please save its URL and delete hash in order to retrieve or remove the picture in the future.