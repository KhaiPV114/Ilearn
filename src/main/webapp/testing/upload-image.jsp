<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload image test</title>
        <style>
            #image-preview {
                max-width: 600px;
                border-radius: 10px;
            }
        </style>
    </head>
    <body>
        <h1>Upload image test</h1>
        <form action="#" method="post" enctype="multipart/form-data">
            <p>Image:</p>
            <img id="image-preview" src="#" alt="image preview" />
            </br>
            <input id="image" name="image" type="file" accept=".jpg, .jpeg, .png"/>
            <br/>
            <input type="submit">
        </form>
        <a href="${pageContext.request.contextPath}${link}">${link}</a>
        <p>${message}</p>
        <script>
            let imagePreviewDiv = document.getElementById("image-preview");
            let imageInput = document.getElementById("image");
            imageInput.addEventListener('change', (event) => {
                if (event.target.files) {
                    let [image] = event.target.files;
                    imagePreviewDiv.src = URL.createObjectURL(image);
                }
            });
        </script>
    </body>
</html>